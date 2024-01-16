import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  constructor(private token:StorageService,private router:Router){}

  logout(){
    this.token.clean();
    this.router.navigateByUrl("/login");
    window.location.reload();
  }

  isloggedin:boolean = this.token.isLoggedIn();
}
