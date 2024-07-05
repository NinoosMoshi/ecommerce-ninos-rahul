import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../shared/models/product';
import { StoreService } from './store.service';
import { Brand } from '../shared/models/brand';
import { Type } from '../shared/models/type';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrl: './store.component.scss',
})
export class StoreComponent implements OnInit {
  @Input() title: string = '';
  products: Product[] = [];
  brands: Brand[] = [];
  types: Type[] = [];
  selectedBrand: Brand | null = null;
  selectedType: Type | null = null;

  constructor(private storeService: StoreService) {}

  ngOnInit(): void {
    // initialize selectedBrand and selectedType to "All"
    this.selectedBrand = { id: 0, name: 'All' };
    this.selectedType = { id: 0, name: 'All' };

    // check if both selectedBrand and selectedType are "All"
    if (this.selectedBrand.id === 0 && this.selectedType.id === 0) {
      this.fetchProducts(); // fetch all record without brand and type filtering
    } else {
      // Fetch products with the selected brand and type
      this.fetchProducts();
    }

    this.fetchBrands();
    this.fetchTypes();
  }

  fetchProducts() {
    // pass selected brand/type ids
    const brandId = this.selectedBrand?.id;
    const typeId = this.selectedType?.id;

    this.storeService.getProducts(brandId, typeId).subscribe({
      next: (res) => {
        this.products = res.content;
      },
      error: (err) => {
        console.log(err.message);
      },
    });
  }

  fetchBrands() {
    this.storeService.getBrands().subscribe({
      next: (res) => {
        this.brands = [{ id: 0, name: 'All' }, ...res];
      },
      error: (err) => {
        console.log(err.message);
      },
    });
  }

  fetchTypes() {
    this.storeService.getTypes().subscribe({
      next: (res) => {
        this.types = [{ id: 0, name: 'All' }, ...res];
      },
      error: (err) => {
        console.log(err.message);
      },
    });
  }

  selectBrand(brand: Brand) {
    // update the selectedBrand and fetch products
    this.selectedBrand = brand;
    this.fetchProducts();
  }

  selectType(type: Type) {
    // update the selectedType and fetch products
    this.selectedType = type;
    this.fetchProducts();
  }
}
