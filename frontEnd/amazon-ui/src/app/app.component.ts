import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Product } from './shared/models/product';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  title = 'sport center';

  products: Product[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<ProductData>('http://localhost:8080/api/products').subscribe({
      next: (res) => {
        this.products = res.content;
      },
      error: (err) => {
        console.log(err.message);
      },
    });
  }
}

interface ProductData {
  content: Product[];
}
