import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductData } from '../shared/models/productData';
import { Observable } from 'rxjs';
import { Brand } from '../shared/models/brand';
import { Type } from '../shared/models/type';

@Injectable({
  providedIn: 'root',
})
export class StoreService {
  private apiUrl = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) {}

  // getProducts(): Observable<ProductData> {
  //   return this.http.get<ProductData>(this.apiUrl);
  // }
  getProducts(brandId?: number, typeId?: number): Observable<ProductData> {
    // construct the base URL
    let url = `${this.apiUrl}?`;

    // check if brandId is not 0, and add it to the URL
    if (brandId && brandId !== 0) {
      url = url + `brandId=${brandId}&`;
    }

    // check if typeId is not 0, and add it to the URL
    if (typeId && typeId !== 0) {
      url = url + `typeId=${typeId}&`;
    }

    //remove the trailing '&' if it exists
    if (url.endsWith('&')) {
      url = url.slice(0, -1);
    }

    return this.http.get<ProductData>(url);
  }

  getBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>(`${this.apiUrl}/brands`);
  }

  getTypes(): Observable<Type[]> {
    return this.http.get<Type[]>(`${this.apiUrl}/types`);
  }
}
