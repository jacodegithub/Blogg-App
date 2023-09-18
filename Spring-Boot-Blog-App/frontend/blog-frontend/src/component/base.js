import React from 'react'
import BlogNavbar from './navbar'

export const Base = ({title = "welcome to the blogging app", children}) => {
  return (
    <div className='container-fluid p-0 m-0'>
        <BlogNavbar />
        {children}
    </div>
  )
}
