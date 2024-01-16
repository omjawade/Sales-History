import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SalesService {

  constructor(private http:HttpClient) { }

  rootUrl:string ='http://localhost:9090/api/v1/sales'

  public getMostSoldProduct(){
    return this.http.get(`${this.rootUrl}/most`)
  }

  public getAll(){
    return this.http.get(`${this.rootUrl}`)
  }

  public getByQuarter(searchParam:string){
    return this.http.get(`${this.rootUrl}/quarter?quarter=${searchParam}`)
  }

  public getByDate(searchParam:string){
    return this.http.get(`${this.rootUrl}/date?date=${searchParam}`)
  }

  public getQuantityCategorywise(){
    console.log("got called in serv");
    return this.http.get(`${this.rootUrl}/qtys/categorywise`)
  }

  public getQuantityCategorywiseForYear(searchParam:number){
    return this.http.get(`${this.rootUrl}/qtys/categorywise/year/${searchParam}`)
  }

  public getAmountSoldCategorywise(){
    console.log("got called in serv");
    return this.http.get(`${this.rootUrl}/sold/categorywise`)
  }

  public getAmountSoldCategorywiseForYear(searchParam:number){
    return this.http.get(`${this.rootUrl}/sold/categorywise/year/${searchParam}`)
  }

}
