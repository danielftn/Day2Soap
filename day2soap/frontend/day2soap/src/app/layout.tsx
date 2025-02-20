import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={`bg-[#ffffff] relative w-full h-screen`}>
        <div className="bg-[#A3EBFF] absolute top-0 w-full h-2/3 blur-[3rem]"></div>
        <div className="bg-[#b4ecfc] absolute top-1/3 w-full h-2/3 blur-[3rem]"></div>
        <div className="bg-[#c9f7ff] absolute bottom-0 w-full h-1/3 blur-[3rem]"></div>
        {children}
      </body>
    </html>
  );
}
