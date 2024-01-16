import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './services/authguard.service';
import { ChannelAggregateSaleComponent } from './ChannelComponents/channel-aggregate-sale/channel-aggregate-sale.component';
import { ChannelaggregaterevenueComponent } from './ChannelComponents/channelaggregaterevenue/channelaggregaterevenue.component';
import { ChanneltableComponent } from './ChannelComponents/channeltable/channeltable.component';
import { AddcountryComponent } from './CountryComponents/addcountry/addcountry.component';
import { GetCountOfCustomersComponent } from './CountryComponents/get-count-of-customers/get-count-of-customers.component';
import { ShowcountryComponent } from './CountryComponents/showcountry/showcountry.component';
import { UpdatecountryComponent } from './CountryComponents/updatecountry/updatecountry.component';
import { AddcustomerComponent } from './CustomerComponents/addcustomer/addcustomer.component';
import { ShowcustomerComponent } from './CustomerComponents/showcustomer/showcustomer.component';
import { UpdateCustomerCreditLimitComponent } from './CustomerComponents/update-customer-credit-limit/update-customer-credit-limit.component';
import { UpdatecustomerComponent } from './CustomerComponents/updatecustomer/updatecustomer.component';
import { HomeComponent } from './MiscellaneousComponents/home/home.component';
import { LoginComponent } from './MiscellaneousComponents/login/login.component';
import { SignupComponent } from './MiscellaneousComponents/signup/signup.component';
import { AddproductComponent } from './ProductComponents/addproduct/addproduct.component';
import { DuplicateproductComponent } from './ProductComponents/duplicateproduct/duplicateproduct.component';
import { GetproductsbychannelComponent } from './ProductComponents/getproductsbychannel/getproductsbychannel.component';
import { ShowproductComponent } from './ProductComponents/showproduct/showproduct.component';
import { UpdateproductComponent } from './ProductComponents/updateproduct/updateproduct.component';
import { GetCategorywiseSalesForYearComponent } from './SalesComponents/get-categorywise-sales-for-year/get-categorywise-sales-for-year.component';
import { GetCategorywiseSalesComponent } from './SalesComponents/get-categorywise-sales/get-categorywise-sales.component';
import { MostsoldproductComponent } from './SalesComponents/mostsoldproduct/mostsoldproduct.component';
import { ShowsalesComponent } from './SalesComponents/showsales/showsales.component';


const routes: Routes = [
  
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  // add paths
  { path: 'addcustomer', component: AddcustomerComponent,canActivate:[AuthGuard] },
  { path: 'addcountry', component: AddcountryComponent,canActivate:[AuthGuard] },
  { path: 'addproduct', component: AddproductComponent,canActivate:[AuthGuard] },

  //main show paths
  { path: 'showcustomer', component: ShowcustomerComponent,canActivate:[AuthGuard] },
  { path: 'showcountry', component: ShowcountryComponent,canActivate:[AuthGuard] },
  { path: 'showsales', component: ShowsalesComponent,canActivate:[AuthGuard] },
  { path: 'showproduct', component: ShowproductComponent,canActivate:[AuthGuard] },

  //update paths
  { path: 'updatecustomer/:custId', component: UpdatecustomerComponent ,canActivate:[AuthGuard]},
  { path: 'updatecountry/:countryId', component: UpdatecountryComponent,canActivate:[AuthGuard] },
  { path: 'updateproduct/:prodId', component: UpdateproductComponent,canActivate:[AuthGuard] },
  { path: 'updatecreditlimit/:custId', component: UpdateCustomerCreditLimitComponent,canActivate:[AuthGuard] },

  //custom show paths
  { path: 'aggrsales', component: ChannelAggregateSaleComponent ,canActivate:[AuthGuard]},
  { path: 'channelaggregaterevenue', component: ChannelaggregaterevenueComponent,canActivate:[AuthGuard] },
  { path: 'channelstable', component: ChanneltableComponent,canActivate:[AuthGuard] },
  { path: 'mostsoldproduct', component: MostsoldproductComponent,canActivate:[AuthGuard] },
  { path: 'channelstable', component: ChanneltableComponent,canActivate:[AuthGuard] },
  { path: 'channelaggregaterevenue', component: ChannelaggregaterevenueComponent },
  { path: 'duplicate', component: DuplicateproductComponent ,canActivate:[AuthGuard]},
  { path: 'productsbychannel', component: GetproductsbychannelComponent ,canActivate:[AuthGuard]},
  { path: 'countrywisecustomercount', component: GetCountOfCustomersComponent,canActivate:[AuthGuard] },
  { path: 'categorywisesales', component: GetCategorywiseSalesComponent ,canActivate:[AuthGuard]},
  { path: 'categorywisesalesforyear', component: GetCategorywiseSalesForYearComponent,canActivate:[AuthGuard] },

  //miscellaneous paths
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: '**', redirectTo: 'home' }

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

