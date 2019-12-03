import { NgModule } from '@angular/core';

import { NiCnewSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [NiCnewSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [NiCnewSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class NiCnewSharedCommonModule {}
