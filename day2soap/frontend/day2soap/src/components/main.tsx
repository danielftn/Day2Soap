"use client"

import Link from "next/link"
import React from 'react'
import { useState } from 'react';
import RecommendationResults from "./recommendationResults";

export default function Main() {
    const [favouriteMovie, setFavouriteMovie] = useState('');
    const [favouriteGenre, setFavouriteGenre] = useState('');
    const [favouriteActor, setFavouriteActor] = useState('');
    const [favouriteDirector, setFavouriteDirector] = useState('');
    const [mpaRating, setMpaRating] = useState('');
    const [productionDecade, setProductionDecade] = useState('');
    const [productionLength, setProductionLength] = useState('');
    const [showRecommendation, setShowRecommendation] = useState(false);
    
    const updateMovie = (event) => {
        setFavouriteMovie(event.target.value);
    };
    
    const updateGenre = (event) => {
        setFavouriteGenre(event.target.value);
    };
    
    const updateActor = (event) => {
        setFavouriteActor(event.target.value);
    };
    
    const updateDirector = (event) => {
        setFavouriteDirector(event.target.value);
    };
    
    const updateMpaRating = (event) => {
        setMpaRating(event.target.value);
    };
    
    const updateProductionDecade = (event) => {
        setProductionDecade(event.target.value);
    };
    
    const updateProductionLength = (event) => {
        setProductionLength(event.target.value);
        console.log(productionLength)
    };
    
    const handleRecommendation = async (event) => {
        event.preventDefault();
        const recommendationPostRequest = {
            method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    favouriteMovie: favouriteMovie,
                    favouriteGenre: favouriteGenre,
                    favouriteActor: favouriteActor,
                    favouriteDirector: favouriteDirector,
                    mpaRating: mpaRating,
                    productionDecade: productionDecade,
                    productionLength: productionLength
                })
        }

        try{
            const post_response = await fetch('http://localhost:8080/api/recommendation', recommendationPostRequest);
            if(post_response.ok){
                alert('Recommendation generated');
                const data = await post_response.json();
                console.log(data);
            } else {
                alert('Recommendation failed');
                setShowRecommendation(true);
            }
        } catch (error) {
            console.log(error)
        }
    }

  return (
    <main className='items-center m-[4rem] '>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w'>
            <h2 className='text-left text-black text-[1.7rem] mb-5'>Movie Information</h2>
            <form className='m-auto' onSubmit={handleRecommendation}>
                <div id='favouritemovie' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Movie</label>
                    <input onChange={updateMovie} type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Movie' required>
                    </input>
                </div>

                <div id='favouritegenre' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Genre </label>
                    <input onChange={updateGenre} type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Genre' required>
                    </input>
                </div>

                <div id='favouriteactor' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Actor (Optional) </label>
                    <input onChange={updateActor} type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Actor' >
                    </input>
                </div>
                
                <div id='favouritedirector' className='grid grid-cols-1 mb-5'>
                    <label className='text-black'>Favourite Director (Optional) </label>
                    <input onChange={updateDirector}type='text' className='bg-slate-100 text-black rounded p-2' placeholder='Favourite Director' >
                    </input>
                </div>

                <div className='lg:grid lg:grid-cols-3 mb-5 sm:grid-cols-1'>
                    <div id="mparating" className='grid mb-5'>
                        <label className="text-black">MPA Rating</label>
                        <select onChange={updateMpaRating} className='flex bg-slate-100 text-black rounded p-2'>
                            <option>G - General Audience</option>
                            <option>PG - Parental Guidance Suggested</option>
                            <option>PG-13 - Parental Strongly Cautioned</option>
                            <option>R - Restricted</option>
                        </select>
                    </div>

                    <div id="productiondecade" className='grid mb-5 lg:ml-4 '>
                        <label className="text-black">Production Decade</label>
                        <select onChange={updateProductionDecade} className='bg-slate-100 text-black rounded p-2'>
                            <option>&lt; 1980s</option>
                            <option>1990s</option>
                            <option>2000s</option>
                            <option>2010s</option>
                            <option>2020s</option>
                        </select>
                    </div>

                    <div id="productiondecade" className='grid mb-5 lg:ml-4'>
                        <label className="text-black">Production Length</label>
                        <select onChange={updateProductionLength} className='bg-slate-100 text-black rounded p-2'>
                            <option>1h</option>
                            <option>2h</option>
                            <option>&gt; 3h</option>
                        </select>
                    </div>
                </div>
                
                <div id='button' className='flex justify-center align-middle'>
                    <button className='bg-[#a0eafe] p-3 rounded hover:bg-[#c5f2ff] w-full'>
                        <Link href='/main' className='text-white font-bold'>Generate Movie Recommendation</Link>
                    </button>
                </div>
            </form>
        </div>

        <div>
            {showRecommendation ? <RecommendationResults/> : <></>}
        </div>
    </main>
  )
}
