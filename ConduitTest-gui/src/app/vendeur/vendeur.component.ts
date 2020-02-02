import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/httpclient.service';

declare var $: any

@Component({
  selector: 'app-vendeur',
  templateUrl: './vendeur.component.html',
  styleUrls: ['./vendeur.component.scss']
})
export class VendeurComponent implements OnInit {
  constructor(
    private httpClientService: HttpClientService) { }

  ngOnInit() {
    this.jquery_code();
  }

  jquery_code() {
    $(function () {
      $("tbody tr").click(function () {
        $(this).addClass("s1").siblings().removeClass("s1");
        $(this).find(":radio").attr("checked", true);
      });
    });

  }
}