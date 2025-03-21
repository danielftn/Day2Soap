'use client';
import "./globals.css";
import Header from "@/components/header";
import { AuthProvider } from "@/context/AuthContext";
import { UserProvider } from "@/context/UserContext";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    
    <html lang="en">
      <body className={`bg-[#ffffff] relative w-full h-screen`}>
        <div className="bg-[#a0eafe] absolute top-0 w-full h-2/3 blur-[3rem]"></div>
        <div className="bg-[#c5f2ff] absolute top-1/3 w-full h-2/3 blur-[3rem]"></div>
        <div className="bg-[#e0f6fa] absolute bottom-0 w-full h-1/3 blur-[3rem]"></div>
        
        <div className="relative">
        <AuthProvider>
        <UserProvider>
          <Header/>          
          {children}
        </UserProvider>
        </AuthProvider>
          </div> 
      </body>
    </html>
  );
}
