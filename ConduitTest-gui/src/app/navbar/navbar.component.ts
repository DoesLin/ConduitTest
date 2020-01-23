import { Component, OnInit } from '@angular/core';

declare function initDropdown(): any;
declare function initTooltip(): any;

declare var $: any

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  teamDevMail = "dlfr-development-m2m.ext@atos.net"
  documentationLink = "https://kazan.priv.atos.fr/share/data/m2m-core/m2m-suv-parent"

  constructor() { }

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
