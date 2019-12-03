import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NiCnewSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [NiCnewSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [NiCnewSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NiCnewSharedModule {
  static forRoot() {
    return {
      ngModule: NiCnewSharedModule
    };
  }
}
