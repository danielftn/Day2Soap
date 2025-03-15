'use client'
import React from 'react'
import Link from "next/link"
import { useAuth } from '@/context/AuthContext'

export default function Header() {
const { isLoggedIn, logout } = useAuth();
  console.log(isLoggedIn)

  return (
    <nav className='flex justify-between align-middle bg-white text-black p-4 shadow-lg sticky top-0'>
      <h1 className='flex lg:text-[1.5rem] sm:text-[1.3rem] my-auto'>Day2Soap</h1>
      <ul className='flex justify-between text-center space-x-[5rem] lg:text-[1.1rem] m-auto'>
        <li>
          <Link href="/main">Home</Link>
        </li>
        <li>
          {isLoggedIn ? <Link href="/history">History</Link> : <Link href="/">History</Link>}
        </li>
        <li>
          {isLoggedIn ? <Link href="/movies">Movies</Link> : <Link href="/">Movies</Link>}
        </li>
      </ul>
      <button className='rounded-md bg-[#A3EBFF] hover:bg-[#C7F0FB] shadow-md p-2'>
        {isLoggedIn ? <Link href="/" className='lg:m-6' onClick={logout}>Logout</Link> : <Link href="/" className='m-6' >Login</Link>}
      </button>

    </nav>
  )
}
