import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from '../Customer';

@Component({
  selector: 'app-addcustomer',
  templateUrl: './addcustomer.component.html',
  styleUrls: ['./addcustomer.component.css']
})
export class AddcustomerComponent {

  constructor(private customerService: CustomerService, private router: Router) { }

  cust: Customer = {
    custId: 0,
    custFirstName: '',
    custLastName: '',
    custGender: '',
    custYearOfBirth:0,
    custMaritalStatus: '',
    custStreetAddress: '',
    custPostalCode: '',
    custCity: '',
    custCityId: 0,
    custStateProvince: '',
    custStateProvinceId: 0,
    countryId: 0,
    custMainPhoneINT: '',
    custIncomeLevel: '',
    custCreditLimit: 0,
    custEmail: '',
    custTotal: '',
    custTotalId: 0,
    custSrcId: 0,
    custEffFrom: new Date(),
    custEffTo: new Date(),
    custValid: '',
    custMainPhoneNumber: ''
  }
  nameRegex = /^[A-Za-z]+$/
  pincodeRegex = /^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$/
  emailRegex = /^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/
  phoneNoRegex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/

  errMsg: string = '';
  err: boolean = true;

  // add logic
  public addCustomer() {
    console.log(this.cust);
    this.customerService.save(this.cust).subscribe((response: any) => {
      console.log(this.cust);
      
      this.err = true
      this.router.navigate(['/showcustomer'])
    }, (error: HttpErrorResponse) => {
      this.errMsg = error.error.errorMessage;
      this.err = false
    }
    )

}

}
