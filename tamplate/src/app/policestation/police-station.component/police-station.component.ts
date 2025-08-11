import { ChangeDetectorRef, Component } from '@angular/core';
import { PoliceStation } from '../../model/police-station.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PoliceStationService } from '../../service/police-station.service';
import { District } from '../../model/district.model';
import { DistrictService } from '../../service/district.service';

@Component({
  selector: 'app-police-station.component',
  standalone: false,
  templateUrl: './police-station.component.html',
  styleUrl: './police-station.component.css'
})
export class PoliceStationComponent {

  policeStations: PoliceStation[] = [];
  districts: District[] = [];
  policeStationForm!: FormGroup;
  editMode = false;
  currentId?: number;

  constructor(
    private psService: PoliceStationService,
    private districtService: DistrictService,
    private fb: FormBuilder,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.loadDistricts();
    this.loadPoliceStations();

    this.policeStationForm = this.fb.group({
      name: ['', Validators.required],
      district: this.fb.group({
        id: [null, Validators.required]
      })
    });
  }

  loadDistricts(): void {
    this.districtService.getAll().subscribe(data => {
      this.districts = data;
      this.cdr.markForCheck();
    });
  }

  loadPoliceStations(): void {
    this.psService.getAll().subscribe(data => {
      this.policeStations = data;
      this.cdr.markForCheck();
    });
  }

  onSubmit(): void {
    if (this.policeStationForm.invalid) return;

    const formValue = this.policeStationForm.value;
    const policeStation: PoliceStation = {
      name: formValue.name,
      district: { id: formValue.district.id }
    };

    if (this.editMode && this.currentId != null) {
      this.psService.update(this.currentId, policeStation).subscribe(() => {
        this.loadPoliceStations();
        this.resetForm();
      });
    } else {
      this.psService.create(policeStation).subscribe(() => {
        this.loadPoliceStations();
        this.resetForm();
      });
    }
  }

  onEdit(ps: PoliceStation): void {
    this.editMode = true;
    this.currentId = ps.id;
    this.policeStationForm.patchValue({
      name: ps.name,
      district: { id: ps.district?.id }
    });
  }

  onDelete(id: number | undefined): void {
    if (id && confirm('Are you sure to delete this police station?')) {
      this.psService.delete(id).subscribe(() => {
        this.loadPoliceStations();
      });
    }
  }

  resetForm(): void {
    this.policeStationForm.reset();
    this.editMode = false;
    this.currentId = undefined;
  }

}
