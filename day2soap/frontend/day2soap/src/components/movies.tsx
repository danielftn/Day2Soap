"use client";
import React, { useState } from "react";

export default function Movies() {
  // using hardcoded movies for now
  const [movies, setMovies] = useState([
    { title: "Iron Man", year: 2004, checked: false },
    { title: "Iron Man 2", year: 2008, checked: false },
    { title: "Iron Man 3", year: 2013, checked: false },
  ]);

  const updateMovies = (movies) => {
    // need to get movies from the backend server using the users id
    setMovies(movies);
  }

  // Places checkboxed movies at the top of the list
  const handleCheckboxChange = (title) => {
    setMovies((prevMovies) =>
      prevMovies.map((movie) =>
        movie.title === title ? { ...movie, checked: !movie.checked } : movie
      )
    );
  };

  // Sorts the Movies in Alphabetical order
  const sortedMovies = [...movies].sort(
    (a, b) => Number(b.checked) - Number(a.checked)
  );

  return (
    <main className="flex justify-center align-middle items-center m-[8rem]">
      <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w mx-10">
        <h2 className="text-left text-black text-[1.7rem] mb-5">Movies</h2>
        <div
          id="movies-box"
          className=" flex justify-center bg-slate-100 p-6 rounded-lg shadow-lg w-9/10 mx-10"
        >
          <ul className="flex flex-col justify-start w-full" id="movies-list">
            {sortedMovies.map((movie) => (
              <li
                key={movie.title}
                className="text-left text-black flex items-center mb-1"
              >
                <input type="checkbox" className="mr-2 w-4 h-4" onChange={() => handleCheckboxChange(movie.title)} />
                <label className="font-bold">{movie.title} ({movie.year})</label>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </main>
  );
}
