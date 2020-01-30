import { Component, OnInit } from '@angular/core';
import { HttpClientService, Vendeur } from '../service/httpclient.service';
import { Router } from '@angular/router';
import { ArticleDataService } from '../service/article-data.service';
import { VendeurDataService } from '../service/vendeur-data.service';

declare var $: any

@Component({
  selector: 'app-vendeurs',
  templateUrl: './vendeurs.component.html',
  styleUrls: ['./vendeurs.component.scss']
})
export class VendeursComponent implements OnInit {

  vendeurs: Vendeur[];
  vendeurModif = new Vendeur('', '', '');

  constructor(private router: Router,
    private httpClientService: HttpClientService,
    private vendeurdata: VendeurDataService) { }

  ngOnInit() {
    this.jquery_code();
    this.httpClientService.getVendeurs().subscribe(
      response => this.handleSuccessfulResponse(response),
    );
    this.vendeurdata.currentVendeur.subscribe(vendeurModif => this.vendeurModif = vendeurModif);

  }

  jquery_code() {

    $(function () {
      $("tbody tr").click(function () {
        $(this).addClass("s1").siblings().removeClass("s1");
        $(this).find(":radio").attr("checked", true);
      });
    });
  }

  handleSuccessfulResponse(response) {
    this.vendeurs = response;
  }

  addVendeur(): void {
    this.router.navigate(['vendeur-ajout']);
  };

  modifyVendeur(vendeur: Vendeur): void {
    this.vendeurdata.changeVendeur(vendeur)
    this.router.navigate(['vendeur-modif']);
  };

  deleteVendeur(vendeur: Vendeur): void {
    console.log(vendeur);
    this.httpClientService.deleteVendeur(vendeur)
      .subscribe(data => {
        this.vendeurs = this.vendeurs.filter(u => u !== vendeur);
      })
  };

  // public currentCdm;

  // public selectCdm(event: any, item: any) {
  //   this.currentCdm = item.Vendeur;
  // }

  // public tableHighLight() {
  //   $("table#aijquery").on("click", function (e) {
  //     $(e.target).parent("tr").addClass("bg-primary").siblings("tr.bg-primary").removeClass("bg-primary");
  //   });
  // }
}