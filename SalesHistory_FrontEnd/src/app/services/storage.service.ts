import { Injectable } from '@angular/core';

const USER_KEY = 'auth-user';
@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() {}

  clean(): void {
    window.sessionStorage.clear();
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return null;
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return true;
    }

    return false;
  }



  public isAuthenticated(): boolean {

    const token = sessionStorage.getItem(USER_KEY);

    // Check if token exists and is not expired

    if (token) {

      const tokenExpiration = this.getTokenExpiration(token);

      // return tokenExpiration > new Date();
      return true;

    }

    return false;

  }

  private getTokenExpiration(token: string): Date {

    // const decodedToken = this.decodeToken(token);

    // if (decodedToken && decodedToken.exp) {

    //   // Token expiration is specified in seconds, convert it to milliseconds

    //   const expirationSeconds = decodedToken.exp;

    //   const expirationMilliseconds = expirationSeconds * 1000;

    //   return new Date(expirationMilliseconds);

    // }

    return new Date();
  }

  // private decodeToken(token: string): any {
  //   try {
  //     const base64Url = token.split('.')[1];
  //     const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  //     const jsonPayload = decodeURIComponent(atob(base64).split('').map((c) => {
  //       return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  //     }).join(''));
  //     return JSON.parse(jsonPayload);
  //   } catch (error) {
  //     console.error('Error decoding token:', error);
  //     return null;
  //   }
  // }
  
}

// import { Injectable } from '@angular/core';

// const USER_KEY = 'auth-user';

// @Injectable({
//   providedIn: 'root'
// })
// export class StorageService {
//   constructor() {}

//   clean(): void {
//     window.sessionStorage.clear();
//   }

//   public saveUser(user: any): void {
//     window.sessionStorage.removeItem(USER_KEY);
//     window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
//   }

//   public getUser(): any {
//     const user = window.sessionStorage.getItem(USER_KEY);
//     if (user) {
//       return JSON.parse(user);
//     }
//     return null;
//   }

//   public isLoggedIn(): boolean {
//     const user = window.sessionStorage.getItem(USER_KEY);
//     return !!user;
//   }

//   public isAuthenticated(): boolean {
//     const token = sessionStorage.getItem(USER_KEY);
//     if (token) {
//       const tokenExpiration = this.getTokenExpiration(token);
//       return tokenExpiration > new Date();
//     }
//     return false;
//   }

//   private getTokenExpiration(token: string): any {
//     try {
//       const base64Url = token.split('.')[1];
//       const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
//       const jsonPayload = decodeURIComponent(
//         atob(base64)
//           .split('')
//           .map((c) => {
//             return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
//           })
//           .join('')
//       );
//       return JSON.parse(jsonPayload).exp * 1000; // Convert expiration to milliseconds
//     } catch (error) {
//       console.error('Error decoding token:', error);
//       return null;
//     }
//   }
// }
