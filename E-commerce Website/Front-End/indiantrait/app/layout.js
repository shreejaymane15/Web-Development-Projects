import { Inter } from "next/font/google";
import "./globals.css";
import StoreProvider from "./StoreProvider";
import { CookiesProvider } from 'next-client-cookies/server';
import AppStore from "@/context/context";



const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "IndianTrait",
  description: "Discover trendy and affordable fashion at IndianTrait, where style meets comfort. Shop our latest collection of [Men's/Women's/Unisex] clothing for a wardrobe that speaks volumes.",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <head>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
      </head>
      <body className={inter.className}>
        <AppStore>
        <CookiesProvider>
        <StoreProvider>
          {children}
        </StoreProvider>
        </CookiesProvider>
        </AppStore>
      </body>
    </html>
  );
}
