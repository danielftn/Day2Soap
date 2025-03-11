import Link from "next/link"
import React from 'react'


export default function main() {
  return (
    <main className='flex justify-center align-middle items-center m-[8rem]'>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w mx-10'>
            <h2 className='text-left text-black text-[1.7rem] mb-5'>Movie Information</h2>
            <form className='m-auto'>
                <div id='favouritemovie' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Movie</label>
                    <input type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Movie' required>
                    </input>
                </div>

                <div id='favouritegenre' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Genre </label>
                    <input type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Genre' required>
                    </input>
                </div>

                <div id='favouriteactor' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Actor </label>
                    <input type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Actor' required>
                    </input>
                </div>

                <div id='favouritedirector' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Director </label>
                    <input type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Director' required>
                    </input>
                </div>

                <div className='flex mb-5'>
                    <div id="mparating" className='grid grid-cols-1 mb-5 w-1/3'>
                        <label className="text-black">MPA Rating</label>
                        <select className='bg-slate-100 text-black rounded p-2'>
                            <option>G - General Audience</option>
                            <option>PG - Parental Guidance Suggested</option>
                            <option>PG-13 - Parental Strongly Cautioned</option>
                            <option>R - Restricted</option>
                        </select>
                    </div>

                    <div id="productiondecade" className='grid grid-cols-1 max-w mb-5 ml-4 w-1/3'>
                        <label className="text-black">Production Decade</label>
                        <select className='bg-slate-100 text-black rounded p-2'>
                            <option>&lt; 1980s</option>
                            <option>1990s</option>
                            <option>2000s</option>
                            <option>2010s</option>
                            <option>2020s</option>
                        </select>
                    </div>

                    <div id="productiondecade" className='grid grid-cols-1 mb-5 ml-4 w-1/3'>
                        <label className="text-black">Production Length</label>
                        <select className='bg-slate-100 text-black rounded p-2'>
                            <option>1h</option>
                            <option>2h</option>
                            <option>&gt; 3h</option>
                        </select>
                    </div>
                </div>
                
                <div id='button' className='flex justify-center align-middle'>
                    <button className='bg-[#a0eafe] p-3 rounded hover:bg-[#c5f2ff] w-full'>
                        <Link href='/' className='text-white font-bold'>Generate Movie Recommendation</Link>
                    </button>
                </div>
            </form>
        </div>
    </main>
  )
}
