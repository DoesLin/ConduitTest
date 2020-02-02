import { Component, OnInit } from '@angular/core';
import { HttpClientService, Vendeur } from '../service/httpclient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vendeur-ajout',
  templateUrl: './vendeur-ajout.component.html',
  styleUrls: ['./vendeur-ajout.component.scss']
})
export class VendeurAjoutComponent implements OnInit {

  vendeur: Vendeur = new Vendeur('', '', '');

  constructor(private router: Router,
    private httpClientService: HttpClientService) { }

  ngOnInit() {
  }

  addVendeur(): void {
    if (this.vendeur.username == '' || this.vendeur.username == null) {
      alert("Vendeur username is empty.");
    } else if (this.vendeur.password == '' || this.vendeur.password == null) {
      alert("Vendeur password is empty.");
    } else {
      this.httpClientService.createVendeur(this.vendeur)
        .subscribe(
          data => {
            alert("Vendeur created successfully.");
            this.router.navigate(['/vendeurs'])
          },
          error => {
            var msg = error['error']['message']
            alert('Creation fail: ' + msg.split(";")[0])
          });

    }
  }

  annuler(): void {
    this.router.navigate(['/vendeurs'])
  }


}
