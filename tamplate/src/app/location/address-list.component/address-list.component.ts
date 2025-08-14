import { ChangeDetectorRef, Component } from '@angular/core';
import { AddressService } from '../../service/address.service';

@Component({
  selector: 'app-address-list.component',
  standalone: false,
  templateUrl: './address-list.component.html',
  styleUrl: './address-list.component.css'
})
export class AddressListComponent {
  addresses: any[] = [];

  constructor(private addressService: AddressService,
    private cd: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.loadAddresses();
  }

  loadAddresses() {
    this.addressService.getAllAddresses().subscribe({
      next: (data) => {
        this.addresses = data;
        this.cd.markForCheck();
      },
      error: (err) => {
        console.error('Error fetching addresses', err);
      }
    });
  }

}
