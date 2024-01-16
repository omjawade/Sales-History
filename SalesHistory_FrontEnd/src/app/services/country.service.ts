import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Country } from '../CountryComponents/Country';

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  private rootUrl:string='http://localhost:9090/api/v1/countries';

  constructor(private http:HttpClient) {}

  public findAll=():any=>{
    return this.http.get(`${this.rootUrl}`);
  }
  public save = (countries: Country) => {
    console.log(countries); 
    return this.http.post(`${this.rootUrl}`, countries);
  }
  public getById = (countryId: number) => {
    return this.http.get<Country>(`${this.rootUrl}/${countryId}`);
  }
  public edit(data: Country) {
    return this.http.put<Country>(`${this.rootUrl}`, data)
  }
  public getCountriwiseCustomerCount=()=>{
    return this.http.get(`${this.rootUrl}/count`)
  }
  public getRegionwiseCustomerCount=(region:string)=>{
    return this.http.get(`${this.rootUrl}/${region}/customers`)
  }
}
