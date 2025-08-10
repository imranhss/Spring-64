import { ChangeDetectorRef, Component } from '@angular/core';
import { JobseekerService } from '../../service/jobseeker.service';
import { EducationService } from '../../service/education.service';
import { Education } from '../../model/education';

import * as bootstrap from 'bootstrap';
import { Experience } from '../../model/experience';
import { ExperienceService } from '../../service/experience.service';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import { Extracurricular } from '../../model/extracurricular';
import { ExtracurricularService } from '../../service/extracurricular.service';



@Component({
  selector: 'app-jobseeker-profile.component',
  standalone: false,
  templateUrl: './jobseeker-profile.component.html',
  styleUrl: './jobseeker-profile.component.css'
})
export class JobseekerProfileComponent {

  jobSeeker: any;

  educations: Education[] = [];

  experiences: Experience[] = [];

  newExperience: Experience = { company: '', position: '', fromDate: '' };

  extracurriculars: Extracurricular[] = [];
  newExtracurricular: Extracurricular = { title: '', role: '', description: '' };

  newEducation = {
    level: '',
    institute: '',
    result: '',
    year: ''
  };

  constructor(
    private jobSeekerService: JobseekerService,
    private cdr: ChangeDetectorRef,
    private educationService: EducationService,
    private expService: ExperienceService,
    private extracurricularService: ExtracurricularService
  ) { }

  ngOnInit(): void {
    this.getProfile();
    this.loadEducations();
    this.loadExperiences();
     this.loadExtracurriculars(); 
  }


   // ✅ Load extracurriculars
  loadExtracurriculars(): void {
    this.extracurricularService.getAllExtracurriculars().subscribe({
      next: (data) => {
        this.extracurriculars = data;
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to load extracurriculars', err)
    });
  }


  // ✅ Add extracurricular
  addExtracurricular(): void {
    this.extracurricularService.addExtracurricular(this.newExtracurricular).subscribe({
      next: () => {
        this.newExtracurricular = { title: '', role: '', description: '' };
        this.loadExtracurriculars();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to add extracurricular', err)
    });
  }

   // ✅ Delete extracurricular
  deleteExtracurricular(id: number | undefined): void {
    if (!id) return;
    if (!confirm('Are you sure you want to delete this extracurricular?')) return;

    this.extracurricularService.deleteExtracurricular(id).subscribe({
      next: () => {
        this.loadExtracurriculars();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to delete extracurricular:', err)
    });
  }

  loadEducations(): void {
    this.educationService.getEducations().subscribe({
      next: (data) => {
        this.educations = data;

        this.cdr.markForCheck();

      },
      error: (err) => {
        console.error('Failed to load educations', err);
      }
    });
  }

  getProfile() {

    this.jobSeekerService.getProfile().subscribe({
      next: (data) => {
        this.jobSeeker = data;
        console.log(data);
        this.cdr.markForCheck();

      },
      error: (err) => {
        console.error('Failed to load profile', err);
      }
    });
  }



  addEducation(): void {
    this.educationService.addEducation(this.newEducation).subscribe({
      next: async (addedEdu: any) => {
        if (!this.jobSeeker.educations) {
          this.jobSeeker.educations = [];
        }
        this.jobSeeker.educations.push(addedEdu);
        this.newEducation = { level: '', institute: '', result: '', year: '' };


      },
      error: (err) => {
        console.error('Failed to add education', err);
      }
    });
  }


  deleteEducation(id: number): void {
    if (!confirm('Are you sure you want to delete this education?')) {
      return;
    }

    this.educationService.deleteEducation(id).subscribe({
      next: () => {
        // ✅ Remove from UI
        this.loadEducations();
        this.cdr.markForCheck();

      },
      error: (err) => {
        console.error('Failed to delete education:', err);
        alert('Failed to delete education.');
      }
    });
  }



  loadExperiences(): void {
    this.expService.getAllExperiences().subscribe(data => {
      this.experiences = data;
      this.cdr.markForCheck();
    });
  }

  addExperience(): void {
    this.expService.addExperience(this.newExperience).subscribe(() => {
      this.newExperience = { company: '', position: '', fromDate: '' };
      this.loadExperiences();
      this.cdr.markForCheck();
    });
  }

  deleteExperience(id: number | undefined): void {
    if (!id) return;
    this.expService.deleteExperience(id).subscribe(() => {
      this.loadExperiences();
      this.cdr.markForCheck();
    });
  }


  convertImgToBase64(url: string): Promise<string> {
    return new Promise((resolve, reject) => {
      const img = new Image();
      img.setAttribute('crossOrigin', 'anonymous'); // avoid CORS issues
      img.onload = () => {
        const canvas = document.createElement('canvas');
        canvas.width = img.width;
        canvas.height = img.height;

        const ctx = canvas.getContext('2d');
        ctx?.drawImage(img, 0, 0);

        const dataURL = canvas.toDataURL('image/png');
        resolve(dataURL);
      };
      img.onerror = error => reject(error);
      img.src = url;
    });
  }


  async generateCV() {
    const doc = new jsPDF('p', 'pt', 'a4');
    const marginLeft = 40;
    let y = 40;

    // Convert image URL to base64
    const imageUrl = `http://localhost:8085/images/jobSeeker/${this.jobSeeker.photo}`;
    let imgData = '';
    try {
      imgData = await this.convertImgToBase64(imageUrl);
    } catch (e) {
      console.warn('Could not load image for PDF:', e);
    }

    // Add image if available (width: 100px, height: auto)
    if (imgData) {
      doc.addImage(imgData, 'PNG', marginLeft, y, 100, 100);
    }

    // Adjust starting y position after image
    y += 110;

    // Title
    doc.setFontSize(22);
    doc.text(`${this.jobSeeker.name}'s CV`, marginLeft, y);
    y += 30;

    // Personal info
    doc.setFontSize(12);
    doc.text(`Email: ${this.jobSeeker.email}`, marginLeft, y);
    y += 15;
    doc.text(`Phone: ${this.jobSeeker.phone}`, marginLeft, y);
    y += 15;
    doc.text(`Gender: ${this.jobSeeker.gender}`, marginLeft, y);
    y += 15;
    doc.text(`Address: ${this.jobSeeker.address}`, marginLeft, y);
    y += 30;

    // Experiences section (same as before) ...
    // ... (rest of your code here, unchanged)

    // For brevity, reuse your existing code to add experiences and education below

    // Save PDF
    doc.save(`${this.jobSeeker.name}_CV.pdf`);
  }


}
