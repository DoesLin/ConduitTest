import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication/authentication.service';

declare function initDropdown(): any;
declare function initTooltip(): any;

declare var $: any

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private loginService: AuthenticationService) { }

  ngOnInit() {
    initDropdown();
    initTooltip();
    this.jquery_code();
  }

  jquery_code() {
    $(document).ready(function () {
      $('.sidenav').sidenav();
      $('.tabs').tabs();
    });
  }

}
