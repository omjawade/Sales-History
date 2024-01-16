import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  ngOnInit(): void {
    if(this.token.isLoggedIn()){
      this.isloggedin=true
    }
    else{
      this.isloggedin=false
    }
  }
constructor(private token:StorageService){}
isloggedin:boolean = false
}
