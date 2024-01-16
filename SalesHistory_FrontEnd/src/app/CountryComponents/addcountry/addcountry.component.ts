import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CountryService } from 'src/app/services/country.service';
import { Country } from '../Country';

@Component({
  selector: 'app-addcountry',
  templateUrl: './addcountry.component.html',
  styleUrls: ['./addcountry.component.css']
})
export class AddcountryComponent {
  constructor(private countryService: CountryService, private router: Router) { }
  //object
  countries: Country = {
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
  errMsg: string = ''
  err: boolean = false
  //Regexes
  IDRegex = /^(\+?\d{1,3}|\d{1,5})$/
  nameRegex = /^([a-zA-Z ]+)/

  //logic
  public addCuntry() {
    console.log(this.countries);
    this.countryService.save(this.countries).subscribe((value: any) => {
      this.err = false
      this.router.navigate(['/showcountry'])
    },
      (error: HttpErrorResponse) => {
        this.err = true
        this.errMsg = error.error.errorMessage;
      }
    )
  }
}
