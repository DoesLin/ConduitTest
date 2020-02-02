import { Component, OnInit } from '@angular/core';
import { Vendeur, HttpClientService } from '../service/httpclient.service';
import { Router } from '@angular/router';
import { VendeurDataService } from '../service/transferdata/vendeur-data.service';

@Component({
  selector: 'app-vendeur-modif',
  templateUrl: './vendeur-modif.component.html',
  styleUrls: ['./vendeur-modif.component.scss']
})
export class VendeurModifComponent implements OnInit {

  vendeur: Vendeur = new Vendeur('', '', '');

  constructor(private router: Router,
    private httpClientService: HttpClientService,
    private vendeurdata: VendeurDataService) { }

  ngOnInit() {
    this.vendeurdata.currentVendeur.subscribe(vendeur => this.vendeur = vendeur)
    console.log(this.vendeur);
  }

  modifyVendeur(): void {
    console.log(this.vendeur);
    if (this.vendeur.username == '' || this.vendeur.username == null) {
      alert("Vendeur username is empty.");
    } else if (this.vendeur.password == '' || this.vendeur.password == null) {
      alert("Vendeur password is empty.");
    } else {
      this.httpClientService.modifyVendeur(this.vendeur)
        .subscribe(
          data => {
            alert("Vendeur modified successfully.");
            this.router.navigate(['/vendeurs'])
          },
          error => {
            var msg = error['error']['message']
            alert('Modification fail: ' + msg.split(";")[0])
          });
    }

  }

  annuler(): void {
    this.router.navigate(['/vendeurs'])
  }

}
