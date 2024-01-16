import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ChannelaggregaterevenueComponent } from './ChannelComponents/channelaggregaterevenue/channelaggregaterevenue.component';
import { ChanneltableComponent } from './ChannelComponents/channeltable/channeltable.component';
import { AddcountryComponent } from './CountryComponents/addcountry/addcountry.component';
import { ShowcountryComponent } from './CountryComponents/showcountry/showcountry.component';
import { UpdatecountryComponent } from './CountryComponents/updatecountry/updatecountry.component';
import { AddcustomerComponent } from './CustomerComponents/addcustomer/addcustomer.component';
import { ShowcustomerComponent } from './CustomerComponents/showcustomer/showcustomer.component';
import { UpdatecustomerComponent } from './CustomerComponents/updatecustomer/updatecustomer.component';
import { HomeComponent } from './MiscellaneousComponents/home/home.component';
import { LoginComponent } from './MiscellaneousComponents/login/login.component';
import { NavigationComponent } from './MiscellaneousComponents/navigation/navigation.component';
import { SignupComponent } from './MiscellaneousComponents/signup/signup.component';
import { AddproductComponent } from './ProductComponents/addproduct/addproduct.component';
import { ShowproductComponent } from './ProductComponents/showproduct/showproduct.component';
import { UpdateproductComponent } from './ProductComponents/updateproduct/updateproduct.component';
import { DuplicateproductComponent } from './ProductComponents/duplicateproduct/duplicateproduct.component';
import { GetproductsbychannelComponent } from './ProductComponents/getproductsbychannel/getproductsbychannel.component';
import { GetCountOfCustomersComponent } from './CountryComponents/get-count-of-customers/get-count-of-customers.component';
import { ChannelAggregateSaleComponent } from './ChannelComponents/channel-aggregate-sale/channel-aggregate-sale.component';
import { MostsoldproductComponent } from './SalesComponents/mostsoldproduct/mostsoldproduct.component';
import { ShowsalesComponent } from './SalesComponents/showsales/showsales.component';
import { GetCategorywiseSalesComponent } from './SalesComponents/get-categorywise-sales/get-categorywise-sales.component';
import { GetCategorywiseSalesForYearComponent } from './SalesComponents/get-categorywise-sales-for-year/get-categorywise-sales-for-year.component';
import { UpdateCustomerCreditLimitComponent } from './CustomerComponents/update-customer-credit-limit/update-customer-credit-limit.component';

@NgModule({
  declarations: [
    AppComponent,
    ShowproductComponent,
    AddproductComponent,
    UpdateproductComponent,
    ShowsalesComponent,
    ChanneltableComponent,
    ChannelaggregaterevenueComponent,
    MostsoldproductComponent,
    ShowcountryComponent,
    AddcountryComponent,
    UpdatecountryComponent,
    AddcustomerComponent,
    UpdatecustomerComponent,
    ShowcustomerComponent,
    HomeComponent,
    SignupComponent,
    NavigationComponent,
    LoginComponent,
    DuplicateproductComponent,
    GetproductsbychannelComponent,
    GetCountOfCustomersComponent,
    ChannelAggregateSaleComponent,
    GetCategorywiseSalesComponent,
    GetCategorywiseSalesForYearComponent,
    UpdateCustomerCreditLimitComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }