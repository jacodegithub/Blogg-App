import React from 'react'
import { Card, CardBody, CardFooter, CardHeader } from 'reactstrap'

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
            <CardFooter>like, share, view</CardFooter>
        </Card>
    </div>
  )
}
