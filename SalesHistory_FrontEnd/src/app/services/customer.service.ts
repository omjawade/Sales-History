import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../CustomerComponents/Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private rootUrl:string='http://localhost:9090/api/v1/customers';

  constructor(private http:HttpClient) { }

  public findAll=():any=>{
    return this.http.get(`${this.rootUrl}`);
  }

  public findByCity(searchQuery:string){
    return this.http.get(`${this.rootUrl}/customercity/${searchQuery}`)
  }

  public findByFirstName(searchQuery:string){
    return this.http.get(`${this.rootUrl}/customername/${searchQuery}`);
  }

  public findByIncome(searchQuery:string){
    return this.http.get(`${this.rootUrl}/customersincome/${searchQuery}`);
  }

  public save = (customer: Customer) => {
    console.log(customer);
    return this.http.post(`${this.rootUrl}`, customer);
  }
  public getById = (custId: number) => {
    console.log("in getbyId");
    return this.http.get<Customer>(`${this.rootUrl}/${custId}`);
  }
  public edit(data: Customer) {
    console.log(data);
    
    return this.http.put(`${this.rootUrl}`, data)
  }

  public getBetweenCreditLimit(beginLimit:number,endLimit:number){
    return this.http.get(`${this.rootUrl}/limit/${beginLimit}/${endLimit}`)
  }

  public updateCreditLimit(newData:Customer){
    console.log(newData.custId);
    
    return this.http.put(`${this.rootUrl}/${newData.custId}/creditLimit`,newData)
  }
}
