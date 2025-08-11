import { ChangeDetectorRef, Component } from '@angular/core';
import { Country } from '../../model/country.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CountryService } from '../../service/country.service';

@Component({
  selector: 'app-country-component',
  standalone: false,
  templateUrl: './country-component.html',
  styleUrl: './country-component.css'
})
export class CountryComponent {

  countries: Country[] = [];
  countryForm!: FormGroup;
  editMode = false;
  editId?: number;

  constructor(private countryService: CountryService, private fb: FormBuilder, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadCountries();
    this.countryForm = this.fb.group({
      name: ['', Validators.required]
    });
  }

 loadCountries(): void {
  this.countryService.getAll().subscribe(data => {
    this.countries = data;
    this.cdr.markForCheck();
  });
}


  onSubmit(): void {
    if (this.countryForm.invalid) return;

    const countryData: Country = this.countryForm.value;

    if (this.editMode && this.editId !== undefined) {
      this.countryService.update(this.editId, countryData).subscribe(() => {
        this.loadCountries();
        this.resetForm();
        this.cdr.markForCheck();
      });
    } else {
      this.countryService.create(countryData).subscribe(() => {
        this.loadCountries();
        this.resetForm();
        this.cdr.markForCheck();
      });
    }
  }

  onEdit(country: Country): void {
    this.editMode = true;
    this.editId = country.id;
    this.countryForm.patchValue({
      name: country.name
    });
    this.cdr.markForCheck();
  }

  onDelete(id?: number): void {
    if (id && confirm('Are you sure you want to delete this country?')) {
      this.countryService.delete(id).subscribe(() => {
        this.loadCountries();
        this.cdr.markForCheck();
      });
    }
  }

  resetForm(): void {
    this.editMode = false;
    this.editId = undefined;
    this.countryForm.reset();
  }

}
