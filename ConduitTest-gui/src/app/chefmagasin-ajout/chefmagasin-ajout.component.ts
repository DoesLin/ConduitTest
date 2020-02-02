import { Component, OnInit } from '@angular/core';
import { ChefMagasin, HttpClientService } from '../service/httpclient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chefmagasin-ajout',
  templateUrl: './chefmagasin-ajout.component.html',
  styleUrls: ['./chefmagasin-ajout.component.scss']
})
export class ChefmagasinAjoutComponent implements OnInit {

  chefMagasin: ChefMagasin = new ChefMagasin('', '', '');

  constructor(private router: Router,
    private httpClientService: HttpClientService) { }

  ngOnInit() {
  }

  addChefMagasin(): void {
    if (this.chefMagasin.username == '' || this.chefMagasin.username == null) {
      alert("ChefMagasin username is empty.");
    } else if (this.chefMagasin.password == '' || this.chefMagasin.password == null) {
      alert("ChefMagasin password is empty.");
    } else {
      this.httpClientService.createVendeur(this.chefMagasin)
        .subscribe(
          data => {
            alert("ChefMagasin created successfully.");
            this.router.navigate(['/chefmagasins'])
          },
          error => {
            var msg = error['error']['message']
            alert('Creation fail: ' + msg.split(";")[0])
          });

    }
  }

  annuler(): void {
    this.router.navigate(['/chefmagasins'])
  }

}
