import { ChangeDetectorRef, Component } from '@angular/core';
import { AddressService } from '../../service/address.service';

@Component({
  selector: 'app-address-form.component',
  standalone: false,
  templateUrl: './address-form.component.html',
  styleUrl: './address-form.component.css'
})
export class AddressFormComponent {


   countries: any[] = [];
  divisions: any[] = [];
  districts: any[] = [];
  policeStations: any[] = [];

  selectedCountry: number = 0;
  selectedDivision: number = 0;
  selectedDistrict: number = 0;
  selectedPoliceStation: number = 0;

  addressLine1: string = '';
  addressLine2: string = '';

  constructor(private addressService: AddressService,

    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadCountries();
  }

  loadCountries() {
    this.addressService.getCountries().subscribe(data => {
      this.countries = data;
    });
  }

  onCountryChange() {
    this.divisions = [];
    this.districts = [];
    this.policeStations = [];
    this.selectedDivision = 0;
    this.selectedDistrict = 0;
    this.selectedPoliceStation = 0;

    if (this.selectedCountry) {
      this.addressService.getDivisionsByCountry(this.selectedCountry).subscribe(data => {
        this.divisions = data;
        this.cd.markForCheck();
      });
    }
  }

  onDivisionChange() {
    this.districts = [];
    this.policeStations = [];
    this.selectedDistrict = 0;
    this.selectedPoliceStation = 0;

    if (this.selectedDivision) {
      this.addressService.getDistrictsByDivision(this.selectedDivision).subscribe(data => {
        this.districts = data;
        this.cd.markForCheck();
      });
    }
  }

  onDistrictChange() {
    this.policeStations = [];
    this.selectedPoliceStation = 0;

    if (this.selectedDistrict) {
      this.addressService.getPoliceStationsByDistrict(this.selectedDistrict).subscribe(data => {
        this.policeStations = data;
        this.cd.markForCheck();
      });
    }
  }

  saveAddress() {
    const address = {
      addressLine1: this.addressLine1,
      addressLine2: this.addressLine2,
      country: { id: this.selectedCountry },
      division: { id: this.selectedDivision },
      district: { id: this.selectedDistrict },
      policeStation: { id: this.selectedPoliceStation }
    };

    this.addressService.saveAddress(address).subscribe(() => {
      this.cd.markForCheck();
      alert('Address saved successfully!');
    });
  }

}
