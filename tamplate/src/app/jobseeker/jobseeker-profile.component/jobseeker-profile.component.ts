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
import { Skill } from '../../model/skill';
import { SkillService } from '../../service/skill.service';
import { Training } from '../../model/training';
import { TrainingService } from '../../service/training.service';
import { Reference } from '../../model/reference';
import { ReferenceService } from '../../service/reference.service';
import { Language } from '../../model/language';
import { LanguageService } from '../../service/language.service';
import { Hobby } from '../../model/hobby';
import { HobbyService } from '../../service/hobby.service';



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

  skills: Skill[] = [];

  newSkill: Skill = {
    name: '',
    level: ''
  };

  newEducation = {
    level: '',
    institute: '',
    result: '',
    year: ''
  };


  trainings: Training[] = [];

  newTraining: Training = {
    title: '',
    institute: '',
    duration: '',
    description: ''
  };


  references: Reference[] = [];

  newReference: Reference = {
    name: '',
    contact: '',
    relation: ''
  };

  languages: Language[] = [];

  newLanguage: Language = {
    name: '',
    proficiency: ''
  };

  hobbies: Hobby[] = [];

  newHobby: Hobby = {
    name: ''
  };

  constructor(
    private jobSeekerService: JobseekerService,
    private cdr: ChangeDetectorRef,
    private educationService: EducationService,
    private expService: ExperienceService,
    private extracurricularService: ExtracurricularService,
    private skillService: SkillService,
    private trainingService: TrainingService,
    private referenceService: ReferenceService,
    private languageService: LanguageService,
    private hobbyService: HobbyService,
  ) { }

  ngOnInit(): void {
    this.getProfile();
    this.loadEducations();
    this.loadExperiences();
    this.loadExtracurriculars();
    this.loadSkills();
    this.loadTrainings();
    this.loadReferences();
    this.loadLanguages();
    this.loadHobbies();

  }


  loadHobbies(): void {
    this.hobbyService.getAllHobbies().subscribe({
      next: (data) => {
        this.hobbies = data;
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to load hobbies', err)
    });
  }

  addHobby(): void {
    this.hobbyService.addHobby(this.newHobby).subscribe({
      next: () => {
        this.newHobby = { name: '' };
        this.loadHobbies();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to add hobby', err)
    });
  }

  deleteHobby(id: number | undefined): void {
    if (!id) return;
    if (!confirm('Are you sure you want to delete this hobby?')) return;

    this.hobbyService.deleteHobby(id).subscribe({
      next: () => {
        this.loadHobbies();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to delete hobby:', err)
    });
  }

  loadLanguages(): void {
    this.languageService.getAllLanguages().subscribe({
      next: (data) => {
        this.languages = data;
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to load languages', err)
    });
  }

  addLanguage(): void {
    this.languageService.addLanguage(this.newLanguage).subscribe({
      next: () => {
        this.newLanguage = { name: '', proficiency: '' };
        this.loadLanguages();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to add language', err)
    });
  }

  deleteLanguage(id: number | undefined): void {
    if (!id) return;
    if (!confirm('Are you sure you want to delete this language?')) return;

    this.languageService.deleteLanguage(id).subscribe({
      next: () => {
        this.loadLanguages();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to delete language:', err)
    });
  }


  loadReferences(): void {
    this.referenceService.getAllReferences().subscribe({
      next: (data) => {
        this.references = data;
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to load references', err)
    });
  }

  addReference(): void {
    this.referenceService.addReference(this.newReference).subscribe({
      next: () => {
        this.newReference = { name: '', contact: '', relation: '' };
        this.loadReferences();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to add reference', err)
    });
  }

  deleteReference(id: number | undefined): void {
    if (!id) return;
    if (!confirm('Are you sure you want to delete this reference?')) return;

    this.referenceService.deleteReference(id).subscribe({
      next: () => {
        this.loadReferences();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to delete reference:', err)
    });
  }


  loadTrainings(): void {
    this.trainingService.getAllTrainings().subscribe({
      next: (data) => {
        this.trainings = data;
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to load trainings', err)
    });
  }

  addTraining(): void {
    this.trainingService.addTraining(this.newTraining).subscribe({
      next: () => {
        this.newTraining = { title: '', institute: '', duration: '', description: '' };
        this.loadTrainings();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to add training', err)
    });
  }

  deleteTraining(id: number | undefined): void {
    if (!id) return;
    if (!confirm('Are you sure you want to delete this training?')) return;

    this.trainingService.deleteTraining(id).subscribe({
      next: () => {
        this.loadTrainings();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to delete training:', err)
    });
  }


  loadSkills(): void {
    this.skillService.getAllSkills().subscribe({
      next: (data) => {
        this.skills = data;
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to load skills', err)
    });
  }

  // Add a new skill
  addSkill(): void {
    this.skillService.addSkill(this.newSkill).subscribe({
      next: () => {
        this.newSkill = { name: '', level: '' }; // Reset form
        this.loadSkills();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to add skill', err)
    });
  }

  // Delete a skill by ID
  deleteSkill(id: number | undefined): void {
    if (!id) return;
    if (!confirm('Are you sure you want to delete this skill?')) return;

    this.skillService.deleteSkill(id).subscribe({
      next: () => {
        this.loadSkills();
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Failed to delete skill:', err)
    });
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
    const pageWidth = doc.internal.pageSize.getWidth();
    const pageHeight = doc.internal.pageSize.getHeight();

    const leftColWidth = pageWidth * 0.33;
    const rightColWidth = pageWidth - leftColWidth - 40; // 40 for margin
    const margin = 20;
    let yLeft = margin;
    let yRight = margin;

    // Background for left column
    doc.setFillColor(48, 63, 79);  // dark blue-gray
    doc.rect(0, 0, leftColWidth, pageHeight, 'F');

    // Add profile photo circle with white border
    const imageUrl = `http://localhost:8085/images/jobSeeker/${this.jobSeeker.photo}`;
    let imgData = '';
    try {
      imgData = await this.convertImgToBase64(imageUrl);
    } catch (e) {
      console.warn('Could not load image for PDF:', e);
    }
    if (imgData) {
      const imgSize = 100;
      // White circle behind photo
      doc.setDrawColor(255, 255, 255);
      doc.setFillColor(255, 255, 255);
      doc.circle(leftColWidth / 2, yLeft + imgSize / 2, imgSize / 2 + 4, 'FD');
      // Add photo on top
      doc.addImage(imgData, 'PNG', (leftColWidth - imgSize) / 2, yLeft, imgSize, imgSize, undefined, 'FAST');
    }
    yLeft += 130;

    // Name and title in left column
    doc.setFontSize(18);
    doc.setTextColor(255, 255, 255);
    doc.setFont('helvetica', 'bold');
    doc.text(this.jobSeeker.name.toUpperCase(), margin, yLeft);
    yLeft += 25;
    doc.setFontSize(12);
    doc.setFont('helvetica', 'normal');
    doc.text(this.jobSeeker.profession || 'Your Profession', margin, yLeft);
    yLeft += 30;

    // Personal info list in left column
    doc.setFontSize(10);
    const personalInfo = [
      { icon: '👤', text: this.jobSeeker.gender || 'Gender' },
      { icon: '🎂', text: this.jobSeeker.dob || 'DOB' },
      { icon: '📞', text: this.jobSeeker.phone || 'Phone' },
      { icon: '✉️', text: this.jobSeeker.email || 'Email' },
      { icon: '🌍', text: this.jobSeeker.website || 'Website' },
      { icon: '📍', text: this.jobSeeker.address || 'Address' },
    ];
    personalInfo.forEach(info => {
      doc.text(`${info.icon}  ${info.text}`, margin, yLeft);
      yLeft += 15;
    });

    // Draw separator line on left column
    doc.setDrawColor(255, 255, 255);
    doc.line(margin, yLeft, leftColWidth - margin, yLeft);
    yLeft += 20;

    // Skills title in left column
    doc.setFont('helvetica', 'bold');
    doc.text('SKILLS', margin, yLeft);
    yLeft += 20;

    // List skills
    this.skills.forEach(skill => {
      doc.setFont('helvetica', 'bold');
      doc.text(`• ${skill.name}`, margin, yLeft);
      yLeft += 12;
      doc.setFont('helvetica', 'normal');
      doc.text(`- ${skill.level}`, margin + 12, yLeft);
      yLeft += 18;
    });

    // Repeat similar blocks for Honors, Certifications, Interests on left column...
    // Increase yLeft accordingly

    // --- Right column content ---
    yRight = margin;

    // Title: Objective
    doc.setFontSize(14);
    doc.setFont('helvetica', 'bold');
    doc.setTextColor(0, 0, 0);
    doc.text('OBJECTIVE', leftColWidth + margin, yRight);
    yRight += 20;
    doc.setLineWidth(0.5);
    doc.line(leftColWidth + margin, yRight, pageWidth - margin, yRight);
    yRight += 10;

    // Objective text
    doc.setFont('helvetica', 'normal');
    doc.setFontSize(11);
    const objText = this.jobSeeker.objective || "Your objective text here...";
    const objLines = doc.splitTextToSize(objText, rightColWidth);
    doc.text(objLines, leftColWidth + margin, yRight);
    yRight += objLines.length * 14 + 20;

    // Education
    doc.setFont('helvetica', 'bold');
    doc.setFontSize(14);
    doc.text('EDUCATION', leftColWidth + margin, yRight);
    yRight += 20;
    doc.line(leftColWidth + margin, yRight, pageWidth - margin, yRight);
    yRight += 10;
    doc.setFont('helvetica', 'normal');
    doc.setFontSize(11);
    this.educations.forEach(edu => {
      const eduTitle = `• ${edu.institute} (${edu.level})`;
      doc.text(eduTitle, leftColWidth + margin, yRight);
      yRight += 14;
      const eduDetails = ` GPA: ${edu.result || 'N/A'}  Year: ${edu.year || 'N/A'}`;
      doc.text(eduDetails, leftColWidth + margin + 10, yRight);
      yRight += 20;
    });

    // Similarly add Work Experience, Activities with bullets and indentation in right column

    // Finally save the doc
    doc.save(`${this.jobSeeker.name}_Styled_CV.pdf`);
  }



  // async generateCV() {
  //   const doc = new jsPDF('p', 'pt', 'a4');
  //   const marginLeft = 40;
  //   let y = 40;

  //   // Convert image URL to base64
  //   const imageUrl = `http://localhost:8085/images/jobSeeker/${this.jobSeeker.photo}`;
  //   let imgData = '';
  //   try {
  //     imgData = await this.convertImgToBase64(imageUrl);
  //   } catch (e) {
  //     console.warn('Could not load image for PDF:', e);
  //   }

  //   // Add photo
  //   if (imgData) {
  //     doc.addImage(imgData, 'PNG', marginLeft, y, 100, 100);
  //   }
  //   y += 110;

  //   // Title
  //   doc.setTextColor(0, 51, 102);
  //   doc.setFont('helvetica', 'bold');
  //   doc.setFontSize(22);
  //   doc.text(`${this.jobSeeker.name}'s CV`, marginLeft, y);
  //   y += 30;

  //   // Personal Info header
  //   doc.setFillColor(0, 102, 204);
  //   doc.rect(marginLeft, y - 20, 520, 20, 'F');
  //   doc.setTextColor(255, 255, 255);
  //   doc.setFontSize(14);
  //   doc.text('Personal Information', marginLeft + 5, y);
  //   y += 25;

  //   // Personal Info details
  //   doc.setTextColor(0, 0, 0);
  //   doc.setFont('helvetica', 'normal');
  //   doc.setFontSize(12);
  //   doc.text(`Email: ${this.jobSeeker.email}`, marginLeft, y); y += 15;
  //   doc.text(`Phone: ${this.jobSeeker.phone}`, marginLeft, y); y += 15;
  //   doc.text(`Gender: ${this.jobSeeker.gender}`, marginLeft, y); y += 15;
  //   doc.text(`Address: ${this.jobSeeker.address}`, marginLeft, y); y += 30;

  //   // Helper to check page overflow and add new page
  //   const checkPageOverflow = () => {
  //     if (y > 750) {
  //       doc.addPage();
  //       y = 40;
  //     }
  //   };

  //   // Section render function
  //   const renderSection = (title: string, items: string[]) => {
  //     doc.setFillColor(0, 102, 204);
  //     doc.rect(marginLeft, y - 20, 520, 20, 'F');
  //     doc.setTextColor(255, 255, 255);
  //     doc.setFont('helvetica', 'bold');
  //     doc.setFontSize(14);
  //     doc.text(title, marginLeft + 5, y);
  //     y += 25;
  //     doc.setTextColor(0, 0, 0);
  //     doc.setFont('helvetica', 'normal');
  //     doc.setFontSize(12);
  //     for (const item of items) {
  //       checkPageOverflow();
  //       doc.text(item, marginLeft, y);
  //       y += 15;
  //     }
  //     y += 10;
  //   };

  //   // Experiences
  //   let experienceTexts: string[] = [];
  //   this.experiences.forEach(exp => {
  //     experienceTexts.push(`Company: ${exp.company}`);
  //     experienceTexts.push(`Position: ${exp.position}`);
  //     experienceTexts.push(`From: ${exp.fromDate} To: ${exp.toDate || 'Present'}`);
  //     if (exp.description) experienceTexts.push(`Description: ${exp.description}`);
  //     experienceTexts.push(''); // blank line
  //   });
  //   renderSection('Experiences', experienceTexts);

  //   // Education
  //   let educationTexts: string[] = [];
  //   this.educations.forEach(edu => {
  //     educationTexts.push(`Level: ${edu.level}`);
  //     educationTexts.push(`Institute: ${edu.institute}`);
  //     educationTexts.push(`Result: ${edu.result}`);
  //     educationTexts.push(`Year: ${edu.year}`);
  //     educationTexts.push('');
  //   });
  //   renderSection('Education', educationTexts);

  //   // Skills
  //   let skillTexts = this.skills.map(s => `- ${s.name} (${s.level})`);
  //   renderSection('Skills', skillTexts);

  //   // Extracurricular
  //   let extracurricularTexts: string[] = [];
  //   this.extracurriculars.forEach(ex => {
  //     extracurricularTexts.push(`- ${ex.title} (${ex.role})`);
  //     if (ex.description) extracurricularTexts.push(`  ${ex.description}`);
  //     extracurricularTexts.push('');
  //   });
  //   renderSection('Extracurricular Activities', extracurricularTexts);

  //   // Save file
  //   doc.save(`${this.jobSeeker.name}_CV.pdf`);
  // }


  // async generateCV() {
  //   const doc = new jsPDF('p', 'pt', 'a4');
  //   const marginLeft = 40;
  //   let y = 40;

  //   // Convert image URL to base64
  //   const imageUrl = `http://localhost:8085/images/jobSeeker/${this.jobSeeker.photo}`;
  //   let imgData = '';
  //   try {
  //     imgData = await this.convertImgToBase64(imageUrl);
  //   } catch (e) {
  //     console.warn('Could not load image for PDF:', e);
  //   }

  //   // Add image if available (width: 100px, height: auto)
  //   if (imgData) {
  //     doc.addImage(imgData, 'PNG', marginLeft, y, 100, 100);
  //   }

  //   // Adjust starting y position after image
  //   y += 110;

  //   // Title with dark blue color
  //   doc.setTextColor(0, 51, 102);
  //   doc.setFont('helvetica', 'bold');
  //   doc.setFontSize(22);
  //   doc.text(`${this.jobSeeker.name}'s CV`, marginLeft, y);
  //   y += 30;

  //   // Personal info section header
  //   doc.setFillColor(0, 102, 204); // blue
  //   doc.rect(marginLeft, y - 20, 500, 20, 'F'); // filled rect
  //   doc.setTextColor(255, 255, 255); // white
  //   doc.setFontSize(14);
  //   doc.text('Personal Information', marginLeft + 5, y);
  //   y += 25;

  //   // Personal info text in black
  //   doc.setTextColor(0, 0, 0);
  //   doc.setFont('helvetica', 'normal');
  //   doc.setFontSize(12);
  //   doc.text(`Email: ${this.jobSeeker.email}`, marginLeft, y);
  //   y += 15;
  //   doc.text(`Phone: ${this.jobSeeker.phone}`, marginLeft, y);
  //   y += 15;
  //   doc.text(`Gender: ${this.jobSeeker.gender}`, marginLeft, y);
  //   y += 15;
  //   doc.text(`Address: ${this.jobSeeker.address}`, marginLeft, y);
  //   y += 30;

  //   // Experiences section header
  //   doc.setFillColor(0, 102, 204);
  //   doc.rect(marginLeft, y - 20, 500, 20, 'F');
  //   doc.setTextColor(255, 255, 255);
  //   doc.setFontSize(14);
  //   doc.setFont('helvetica', 'bold');
  //   doc.text('Experiences', marginLeft + 5, y);
  //   y += 25;

  //   // List experiences
  //   doc.setTextColor(0, 0, 0);
  //   doc.setFont('helvetica', 'normal');
  //   doc.setFontSize(12);
  //   for (const exp of this.experiences) {
  //     if (y > 750) { // Add new page if near bottom
  //       doc.addPage();
  //       y = 40;
  //     }
  //     doc.text(`Company: ${exp.company}`, marginLeft, y);
  //     y += 15;
  //     doc.text(`Position: ${exp.position}`, marginLeft, y);
  //     y += 15;
  //     doc.text(`From: ${exp.fromDate} To: ${exp.toDate || 'Present'}`, marginLeft, y);
  //     y += 15;
  //     if (exp.description) {
  //       doc.text(`Description: ${exp.description}`, marginLeft, y);
  //       y += 20;
  //     } else {
  //       y += 10;
  //     }
  //   }
  //   y += 10;

  //   // Education section header
  //   doc.setFillColor(0, 102, 204);
  //   doc.rect(marginLeft, y - 20, 500, 20, 'F');
  //   doc.setTextColor(255, 255, 255);
  //   doc.setFontSize(14);
  //   doc.setFont('helvetica', 'bold');
  //   doc.text('Education', marginLeft + 5, y);
  //   y += 25;

  //   // List educations
  //   doc.setTextColor(0, 0, 0);
  //   doc.setFont('helvetica', 'normal');
  //   doc.setFontSize(12);
  //   for (const edu of this.educations) {
  //     if (y > 750) {
  //       doc.addPage();
  //       y = 40;
  //     }
  //     doc.text(`Level: ${edu.level}`, marginLeft, y);
  //     y += 15;
  //     doc.text(`Institute: ${edu.institute}`, marginLeft, y);
  //     y += 15;
  //     doc.text(`Result: ${edu.result}`, marginLeft, y);
  //     y += 15;
  //     doc.text(`Year: ${edu.year}`, marginLeft, y);
  //     y += 20;
  //   }
  //   y += 10;

  //   // Skills section header
  //   doc.setFillColor(0, 102, 204);
  //   doc.rect(marginLeft, y - 20, 500, 20, 'F');
  //   doc.setTextColor(255, 255, 255);
  //   doc.setFontSize(14);
  //   doc.setFont('helvetica', 'bold');
  //   doc.text('Skills', marginLeft + 5, y);
  //   y += 25;

  //   // List skills
  //   doc.setTextColor(0, 0, 0);
  //   doc.setFont('helvetica', 'normal');
  //   doc.setFontSize(12);
  //   for (const skill of this.skills) {
  //     if (y > 750) {
  //       doc.addPage();
  //       y = 40;
  //     }
  //     doc.text(`- ${skill.name} (${skill.level})`, marginLeft, y);
  //     y += 15;
  //   }
  //   y += 10;

  //   // Extracurricular section header
  //   doc.setFillColor(0, 102, 204);
  //   doc.rect(marginLeft, y - 20, 500, 20, 'F');
  //   doc.setTextColor(255, 255, 255);
  //   doc.setFontSize(14);
  //   doc.setFont('helvetica', 'bold');
  //   doc.text('Extracurricular Activities', marginLeft + 5, y);
  //   y += 25;

  //   // List extracurriculars
  //   doc.setTextColor(0, 0, 0);
  //   doc.setFont('helvetica', 'normal');
  //   doc.setFontSize(12);
  //   for (const ex of this.extracurriculars) {
  //     if (y > 750) {
  //       doc.addPage();
  //       y = 40;
  //     }
  //     doc.text(`- ${ex.title} (${ex.role})`, marginLeft, y);
  //     y += 15;
  //     if (ex.description) {
  //       doc.text(`  ${ex.description}`, marginLeft + 15, y);
  //       y += 20;
  //     } else {
  //       y += 10;
  //     }
  //   }
  //   y += 10;

  //   // Save PDF
  //   doc.save(`${this.jobSeeker.name}_CV.pdf`);
  // }



  // async generateCV() {
  //   const doc = new jsPDF('p', 'pt', 'a4');
  //   const marginLeft = 40;
  //   let y = 40;
  //   const lineHeight = 15;
  //   const pageHeight = 841; // A4 height in pt approx

  //   // Helper to check page overflow and add page if needed
  //   const addPageIfNeeded = (currentY: number, extraSpace: number) => {
  //     if (currentY + extraSpace > pageHeight - 60) {
  //       doc.addPage();
  //       return 40; // reset y position for new page
  //     }
  //     return currentY;
  //   };

  //   // Convert image URL to base64
  //   const imageUrl = `http://localhost:8085/images/jobSeeker/${this.jobSeeker.photo}`;
  //   let imgData = '';
  //   try {
  //     imgData = await this.convertImgToBase64(imageUrl);
  //   } catch (e) {
  //     console.warn('Could not load image for PDF:', e);
  //   }

  //   // Add photo if available
  //   if (imgData) {
  //     doc.addImage(imgData, 'PNG', marginLeft, y, 100, 100);
  //   }
  //   y += 110;

  //   // Title
  //   doc.setFontSize(22);
  //   doc.text(`${this.jobSeeker.name}'s CV`, marginLeft, y);
  //   y += 30;

  //   // Personal info
  //   doc.setFontSize(12);
  //   doc.text(`Email: ${this.jobSeeker.email}`, marginLeft, y); y += lineHeight;
  //   doc.text(`Phone: ${this.jobSeeker.phone}`, marginLeft, y); y += lineHeight;
  //   doc.text(`Gender: ${this.jobSeeker.gender}`, marginLeft, y); y += lineHeight;
  //   doc.text(`Address: ${this.jobSeeker.address}`, marginLeft, y); y += 30;

  //   // --- Experiences ---
  //   if (this.experiences?.length > 0) {
  //     doc.setFontSize(16);
  //     y = addPageIfNeeded(y, 20);
  //     doc.text('Experiences', marginLeft, y);
  //     y += 20;

  //     doc.setFontSize(12);
  //     for (const exp of this.experiences) {
  //       y = addPageIfNeeded(y, lineHeight * 5);
  //       doc.text(`Company: ${exp.company}`, marginLeft, y); y += lineHeight;
  //       doc.text(`Position: ${exp.position}`, marginLeft, y); y += lineHeight;
  //       doc.text(`From: ${exp.fromDate} To: ${exp.toDate || 'Present'}`, marginLeft, y); y += lineHeight;
  //       if (exp.description) {
  //         doc.text(`Description: ${exp.description}`, marginLeft, y);
  //         y += lineHeight;
  //       }
  //       y += 5;
  //     }
  //     y += 10;
  //   }

  //   // --- Educations ---
  //   if (this.educations?.length > 0) {
  //     doc.setFontSize(16);
  //     y = addPageIfNeeded(y, 20);
  //     doc.text('Educations', marginLeft, y);
  //     y += 20;

  //     doc.setFontSize(12);
  //     for (const edu of this.educations) {
  //       y = addPageIfNeeded(y, lineHeight * 4);
  //       doc.text(`Level: ${edu.level}`, marginLeft, y); y += lineHeight;
  //       doc.text(`Institute: ${edu.institute}`, marginLeft, y); y += lineHeight;
  //       doc.text(`Result: ${edu.result}`, marginLeft, y); y += lineHeight;
  //       doc.text(`Year: ${edu.year}`, marginLeft, y); y += lineHeight + 5;
  //     }
  //     y += 10;
  //   }

  //   // --- Extracurriculars ---
  //   if (this.extracurriculars?.length > 0) {
  //     doc.setFontSize(16);
  //     y = addPageIfNeeded(y, 20);
  //     doc.text('Extracurriculars', marginLeft, y);
  //     y += 20;

  //     doc.setFontSize(12);
  //     for (const ex of this.extracurriculars) {
  //       y = addPageIfNeeded(y, lineHeight * 4);
  //       doc.text(`Title: ${ex.title}`, marginLeft, y); y += lineHeight;
  //       doc.text(`Role: ${ex.role}`, marginLeft, y); y += lineHeight;
  //       if (ex.description) {
  //         doc.text(`Description: ${ex.description}`, marginLeft, y);
  //         y += lineHeight;
  //       }
  //       y += 5;
  //     }
  //     y += 10;
  //   }

  //   // --- Skills ---
  //   if (this.skills?.length > 0) {
  //     doc.setFontSize(16);
  //     y = addPageIfNeeded(y, 20);
  //     doc.text('Skills', marginLeft, y);
  //     y += 20;

  //     doc.setFontSize(12);
  //     const skillsText = this.skills.map(s => s.name).join(', ');
  //     const splitSkills = doc.splitTextToSize(skillsText, 500);
  //     y = addPageIfNeeded(y, lineHeight * splitSkills.length);
  //     doc.text(splitSkills, marginLeft, y);
  //     y += lineHeight * splitSkills.length + 10;
  //   }

  //   // --- Languages ---
  //   if (this.languages?.length > 0) {
  //     doc.setFontSize(16);
  //     y = addPageIfNeeded(y, 20);
  //     doc.text('Languages', marginLeft, y);
  //     y += 20;

  //     doc.setFontSize(12);
  //     for (const lang of this.languages) {
  //       y = addPageIfNeeded(y, lineHeight);
  //       doc.text(`${lang.name} - ${lang.proficiency}`, marginLeft, y);
  //       y += lineHeight;
  //     }
  //     y += 10;
  //   }

  //   // --- Trainings ---
  //   if (this.trainings?.length > 0) {
  //     doc.setFontSize(16);
  //     y = addPageIfNeeded(y, 20);
  //     doc.text('Trainings', marginLeft, y);
  //     y += 20;

  //     doc.setFontSize(12);
  //     for (const training of this.trainings) {
  //       y = addPageIfNeeded(y, lineHeight * 4);
  //       doc.text(`Title: ${training.title}`, marginLeft, y); y += lineHeight;
  //       doc.text(`Institute: ${training.institute}`, marginLeft, y); y += lineHeight;
  //       doc.text(`Duration: ${training.duration}`, marginLeft, y); y += lineHeight;
  //       if (training.description) {
  //         doc.text(`Description: ${training.description}`, marginLeft, y);
  //         y += lineHeight;
  //       }
  //       y += 5;
  //     }
  //     y += 10;
  //   }

  //   // --- References ---
  //   if (this.references?.length > 0) {
  //     doc.setFontSize(16);
  //     y = addPageIfNeeded(y, 20);
  //     doc.text('References', marginLeft, y);
  //     y += 20;

  //     doc.setFontSize(12);
  //     for (const ref of this.references) {
  //       y = addPageIfNeeded(y, lineHeight * 3);
  //       doc.text(`Name: ${ref.name}`, marginLeft, y); y += lineHeight;
  //       doc.text(`Contact: ${ref.contact}`, marginLeft, y); y += lineHeight;
  //       doc.text(`Relation: ${ref.relation}`, marginLeft, y); y += lineHeight + 5;
  //     }
  //     y += 10;
  //   }

  //   // --- Hobbies ---
  //   if (this.hobbies?.length > 0) {
  //     doc.setFontSize(16);
  //     y = addPageIfNeeded(y, 20);
  //     doc.text('Hobbies', marginLeft, y);
  //     y += 20;

  //     doc.setFontSize(12);
  //     const hobbiesText = this.hobbies.map(h => h.name).join(', ');
  //     const splitHobbies = doc.splitTextToSize(hobbiesText, 500);
  //     y = addPageIfNeeded(y, lineHeight * splitHobbies.length);
  //     doc.text(splitHobbies, marginLeft, y);
  //     y += lineHeight * splitHobbies.length + 10;
  //   }

  //   // Save PDF
  //   doc.save(`${this.jobSeeker.name}_CV.pdf`);
  // }



}
