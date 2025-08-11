import { ChangeDetectorRef, Component } from '@angular/core';
import { District } from '../../model/district.model';
import { Division } from '../../model/division.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DistrictService } from '../../service/district.service';
import { DivisionService } from '../../service/division.service';

@Component({
  selector: 'app-district.component',
  standalone: false,
  templateUrl: './district.component.html',
  styleUrl: './district.component.css'
})
export class DistrictComponent {


  districts: District[] = [];
  divisions: Division[] = [];
  districtForm!: FormGroup;
  editMode = false;
  currentDistrictId?: number;

  constructor(
    private districtService: DistrictService,
    private divisionService: DivisionService,
    private fb: FormBuilder,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.loadDivisions();
    this.loadDistricts();

    this.districtForm = this.fb.group({
      name: ['', Validators.required],
      divisionId: [null, Validators.required]
    });
  }

  loadDivisions(): void {
    this.divisionService.getAll().subscribe(data => {
      this.divisions = data;
      this.cdr.markForCheck();
    });
  }

  loadDistricts(): void {
    this.districtService.getAll().subscribe(data => {
      this.districts = data;
      this.cdr.markForCheck();
    });
  }

  onSubmit(): void {
    if (this.districtForm.invalid) return;

    const formValue = this.districtForm.value;
    const divisionId = formValue.divisionId;
    const districtData: District = {
      name: formValue.name,
      division: { id: divisionId }
    };

    if (this.editMode && this.currentDistrictId != null) {
      this.districtService.update(this.currentDistrictId, districtData).subscribe(() => {
        this.loadDistricts();
        this.resetForm();
      });
    } else {
      this.districtService.create(districtData, divisionId).subscribe(() => {
        this.loadDistricts();
        this.resetForm();
      });
    }
  }

  onEdit(district: District): void {
    this.editMode = true;
    this.currentDistrictId = district.id;

    this.districtForm.patchValue({
      name: district.name,
      divisionId: district.division?.id || null
    });
  }

  onDelete(id?: number): void {
    if (!id) return;
    if (confirm('Are you sure you want to delete this district?')) {
      this.districtService.delete(id).subscribe(() => {
        this.loadDistricts();
      });
    }
  }

  resetForm(): void {
    this.districtForm.reset();
    this.editMode = false;
    this.currentDistrictId = undefined;
  }

}
