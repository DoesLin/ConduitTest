import { Component, OnInit } from '@angular/core';
import { HttpClientService, Article } from '../service/httpclient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-article-ajout',
  templateUrl: './article-ajout.component.html',
  styleUrls: ['./article-ajout.component.scss']
})
export class ArticleAjoutComponent implements OnInit {

  article: Article = new Article('', '', '', '', 0, 0, null);

  constructor(private router: Router,
    private httpClientService: HttpClientService) { }

  ngOnInit() {
  }

  addArticle(): void {
    this.httpClientService.createArticle(this.article)
      .subscribe(
        data => {
          alert("Article created successfully.");
          this.router.navigate([''])
        },
        error => {
          var msg = error['error']['message']
          alert('Creation fail: ' + msg.split(";")[0])
        });

  }

  annuler(): void {
    this.router.navigate([''])
  }

}
