import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Vendeur } from '../httpclient.service';

@Injectable({
  providedIn: 'root'
})
export class VendeurDataService {

  private vendeurSource = new BehaviorSubject(new Vendeur('', '', ''));
  currentVendeur = this.vendeurSource.asObservable();

  constructor() { }

  changeVendeur(vendeur: Vendeur) {
    this.vendeurSource.next(vendeur)
  }
}
