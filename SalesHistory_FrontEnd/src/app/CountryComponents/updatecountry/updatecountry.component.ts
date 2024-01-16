import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CountryService } from 'src/app/services/country.service';
import { __param } from 'tslib';
import { Country } from '../Country';

@Component({
  selector: 'app-updatecountry',
  templateUrl: './updatecountry.component.html',
  styleUrls: ['./updatecountry.component.css']
})
export class UpdatecountryComponent  implements OnInit{
  constructor(private countryService:CountryService,private router:Router,private route:ActivatedRoute ){}
  
  
  ngOnInit(): void {
    this.route.paramMap.subscribe((param) => {
      let countryId=Number(param.get("countryId"))
      console.log(countryId);
      this.getById(countryId)
    })

    }

  // country list
  countries:Country={
    countryId: 0,
    countryIsoCode: '',
    countryName: '',
    countrySubregion: '',
    countrySubregionId: 0,
    countryRegion: '',
    countryRegionId: 0,
    countryTotal: '',
    countryTotalId: 0
  }

  IDRegex=/^(\+?\d{1,3}|\d{1,5})$/
  nameRegex=/^([a-zA-Z ]+)/
     Iso = /@"^\a{2,2}-\a{2,2}"/

  // get country id 
  getById(id:number){
    this.countryService.getById(id).subscribe((data: Country)=>{
      this.countries=data;
    })
  }
  errMsg:string=''
  err:boolean=true

  // update country details 

  edit(){
    this.countryService.edit(this.countries).subscribe({
      next:(data:any)=>{
        this.err=true
        this.router.navigate(['/showcountry'])
      },error:(error:HttpErrorResponse)=>{
        this.err=false
        this.errMsg=error.error.errorMessage;
      }
    })
  }
}
