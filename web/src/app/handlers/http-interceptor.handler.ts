import {
  HttpEvent,
  HttpHandlerFn,
  HttpInterceptorFn,
  HttpRequest,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../services/storage.service';

export const connectionInterceptor: HttpInterceptorFn = (
  req: HttpRequest<any>,
  next: HttpHandlerFn
): Observable<HttpEvent<any>> => {
  const storageService = inject(StorageService);
  const connection = storageService.getConnection();
  if (connection) {
    const cloned = req.clone({
      setHeaders: {
        'X-TenantID': connection.id,
      },
    });
    return next(cloned);
  } else {
    return next(req);
  }
};
