import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreComponent } from './store.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [StoreComponent],
  imports: [CommonModule, FormsModule],
  exports: [StoreComponent],
})
export class StoreModule {}
