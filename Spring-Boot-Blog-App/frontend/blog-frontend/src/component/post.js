import React from 'react'
import { Card, CardBody, CardFooter, CardHeader } from 'reactstrap'
import { Link, NavLink as ReactLink, useParams } from 'react-router-dom'

export const EachPost = ({post}) => {
  
  if(!post) return null;
  
  return (
    <div>
        <Card style={{margin:'1rem 0 1rem 0', boxShadow:'.2rem .2rem .5rem'}}>
            <CardHeader>
              <h4>{post.title}</h4>
            </CardHeader>
            <CardBody>
                {post.description}
            </CardBody>
            <CardFooter>
              <Link className='mx-2' to={``}>like</Link>
              <Link className='mx-2' to={`/post-page/${post.id}`}>View</Link>
              <Link className='mx-2' to={``}>Share</Link>
            </CardFooter>
        </Card>
    </div>
  )
}
