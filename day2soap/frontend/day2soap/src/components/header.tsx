'use client'
import React, { useState } from 'react'
import Link from "next/link"
import { useAuth } from '@/context/AuthContext'

export default function Header() {
  const { isLoggedIn, logout } = useAuth();
  const [isPressed, setIspressed] = useState(false)

  const updateIsPressed = () => {
    setIspressed(!isPressed);
  }

  return (
    <nav className='flex justify-between align-middle bg-white text-black p-4 shadow-lg sticky top-0'>
      <h1 className='flex text-[1.5rem] my-auto'>Day2Soap</h1>
      <ul className='md:flex hidden justify-between sm:space-x-[5rem] lg:text-[1.1rem] m-auto'>
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
      <button className='md:block hidden rounded-md bg-[#A3EBFF] hover:bg-[#C7F0FB] shadow-md p-2'>
        {isLoggedIn ? <Link href="/" className='lg:m-6' onClick={logout}>Logout</Link> : <Link href="/" className='m-6' >Login</Link>}
      </button>
      
      <div className='relative'>
        <div className='md:hidden'>
            <svg onClick={updateIsPressed} xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="size-10">
              <path strokeLinecap="round" strokeLinejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5" />
            </svg>
        </div>

        {isPressed ? 
          <div className='absolute bg-white md:hidden p-5 rounded-md w-[12.5rem] translate-x-[-9.0rem]'>  
            <ul className='flex flex-wrap justify-between'>
              <li className='w-full'>
                <button className='rounded-md bg-[#A3EBFF] hover:bg-[#C7F0FB] shadow-md p-2 w-full mb-2' onClick={updateIsPressed}>
                  <Link href="/main"><b>Home</b></Link>
                  </button>
              </li>

              <li className='w-full'>
                <button className='rounded-md bg-[#A3EBFF] hover:bg-[#C7F0FB] shadow-md p-2 w-full mb-2' onClick={updateIsPressed}>
                  {isLoggedIn ? <Link href="/history"><b>History</b></Link> : <Link href="/"><b>History</b></Link>}
                </button>
              </li>

              <li className='w-full'>
                <button className='rounded-md bg-[#A3EBFF] hover:bg-[#C7F0FB] shadow-md p-2 w-full mb-2' onClick={updateIsPressed}>
                  {isLoggedIn ? <Link href="/movies"><b>Movies</b></Link> : <Link href="/"><b>Movies</b></Link>}
                </button>
              </li>

              <li className='w-full'>
                <button className='rounded-md bg-[#A3EBFF] hover:bg-[#C7F0FB] shadow-md p-2 w-full' onClick={updateIsPressed}>
                  {isLoggedIn ? <Link href="/" className='lg:m-6' onClick={logout}><b>Logout</b></Link> : <Link href="/" className='m-6' ><b>Login</b></Link>}
                </button>
              </li>
            </ul>
          </div>
        : <></> }
      </div>
    </nav>
  )
}
