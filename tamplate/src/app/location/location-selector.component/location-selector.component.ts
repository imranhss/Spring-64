import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { LocationService } from '../../service/location.service';

@Component({
  selector: 'app-location-selector.component',
  standalone: false,
  templateUrl: './location-selector.component.html',
  styleUrl: './location-selector.component.css'
})
export class LocationSelectorComponent  implements OnInit{


  countries: any[] = [];
  divisions: any[] = [];
  districts: any[] = [];
  policeStations: any[] = [];

  selectedCountry?: number;
  selectedDivision?: number;
  selectedDistrict?: number;

  constructor(private locationService: LocationService, 
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.locationService.getCountries().subscribe(data => {
      this.countries = data;
    });
  }

  onCountryChange(): void {
    this.divisions = [];
    this.districts = [];
    this.policeStations = [];
    if (this.selectedCountry) {
      this.locationService.getDivisionsByCountry(this.selectedCountry).subscribe(data => {
        this.divisions = data;
        this.cd.markForCheck();
      });
    }
  }

  onDivisionChange(): void {
    this.districts = [];
    this.policeStations = [];
    if (this.selectedDivision) {
      this.locationService.getDistrictsByDivision(this.selectedDivision).subscribe(data => {
        this.districts = data;
         this.cd.markForCheck();
      });
    }
  }

  onDistrictChange(): void {
    this.policeStations = [];
    if (this.selectedDistrict) {
      this.locationService.getPoliceStationsByDistrict(this.selectedDistrict).subscribe(data => {
        this.policeStations = data;
         this.cd.markForCheck();
      });
    }
  }
  

}
