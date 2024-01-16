import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from '../Customer';

@Component({
  selector: 'app-updatecustomer',
  templateUrl: './updatecustomer.component.html',
  styleUrls: ['./updatecustomer.component.css']
})
export class UpdatecustomerComponent implements OnInit {
  err: boolean = true;
  errMsg: string='';
  constructor(private custService:CustomerService,private router:Router,private route:ActivatedRoute){}
  ngOnInit(): void {
    this.route.paramMap.subscribe((param)=>{
      let custId=Number(param.get("custId"))
      console.log(custId);
      this.getById(custId)
    })
    
  }

  //object
  cust:Customer={
    custId: 0,
    custFirstName: '',
    custLastName: '',
    custGender: '',
    custYearOfBirth: 0,
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

  //regexes
  nameRegex=/^[A-Za-z]+$/
  pincodeRegex=/^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$/
  emailRegex=/^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/
  phoneNoRegex=/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/

  //get obj logic
  getById(custId:number){
    console.log(custId);
    this.custService.getById(custId).subscribe((data: Customer)=>{
      console.log("before sub");
      this.cust=data;
    })
  }
  //put new data logic
  edit(){
    this.custService.edit(this.cust).subscribe((response: any) => {
      this.err=true
      this.router.navigate(['/showcustomer'])
  },(error:Error)=>{
    alert(error.message);
    // this.errMsg=error.error.errorMessage;
    this.err=false;
  });
  }

}
