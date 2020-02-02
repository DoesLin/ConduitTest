import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

export class User {
  constructor(
    public status: string,
  ) { }

}

export class JwtResponse {
  constructor(
    public jwttoken: string,
  ) { }

}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  authenticate(username, password) {
    let respon = this.httpClient.post<any>('http://localhost:8080/authenticate', { username, password })
    return respon.pipe(
      map(
        userData => {
          sessionStorage.setItem('username', username);
          let tokenStr = 'Bearer ' + userData.token;
          sessionStorage.setItem('token', tokenStr);
          sessionStorage.setItem('permission', userData.permission);
          return userData;
        }
      )

    );
  }


  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }

  isVendeur() {
    let user = sessionStorage.getItem('username')
    let permission = sessionStorage.getItem('permission')
    return !(user === null) && (permission === "Vendeur")
  }

  isPermissionChefMagasin() {
    let user = sessionStorage.getItem('username')
    let permission = sessionStorage.getItem('permission')
    return !(user === null) && ((permission === "ChefMagasin") || (permission === "Pdg"))
  }

  isPermissionPdg() {
    let user = sessionStorage.getItem('username')
    let permission = sessionStorage.getItem('permission')
    return !(user === null) && (permission === "Pdg")
  }
}