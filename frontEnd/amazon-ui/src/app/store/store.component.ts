import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../shared/models/product';
import { StoreService } from './store.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrl: './store.component.scss',
})
export class StoreComponent implements OnInit {
  @Input() title: string = '';
  products: Product[] = [];

  constructor(private storeService: StoreService) {}

  ngOnInit(): void {
    this.storeService.getProducts().subscribe({
      next: (res) => {
        this.products = res.content;
      },
      error: (err) => {
        console.log(err.message);
      },
    });
  }
}
