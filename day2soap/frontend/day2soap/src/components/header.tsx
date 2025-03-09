import React from 'react'
import Link from "next/link"

export default function Header() {
  return (
    <nav className='flex justify-between align-middle bg-white sticky text-black lg:p-4 shadow-lg'>
      <h1 className='lg:text-[1.5rem]'>Day2Soap</h1>
      <ul className='flex justify-between text-center space-x-[5rem] lg:text-[1.1rem] m-auto'>
        <li>
          <Link href="/">Home</Link>
        </li>
        <li>
          <Link href="/">History</Link>
        </li>
        <li>
          <Link href="/">Movies</Link>
        </li>
      </ul>
      <button className='rounded-md bg-[#A3EBFF] hover:bg-[#C7F0FB] shadow-md'>
        <Link href="/" className='lg:m-6'>Login</Link>
      </button>

    </nav>
  )
}
