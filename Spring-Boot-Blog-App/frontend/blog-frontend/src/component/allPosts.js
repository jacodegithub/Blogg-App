import React, { useEffect, useState } from 'react'
import { Col, Container, Row } from 'reactstrap'
import { getAllPostsService } from '../service/postService';
import { EachPost } from './post';

export const Posts = () => {

    const [posts, setPosts] = useState([]);

    const [error, setError] = useState({
        errors: {},
        isError: false
    })

    useEffect(() => {
        getAllPostsService().then(data => {
            console.log('posts -> ', data)
            setPosts(data)
        }).catch(error => {
            console.log('posts error ->', error)
            setError({
                errors: error,
                isError: true
            })
        })
    }, [])

  return (
    <div>
        <Container>
            <Row>
                <Col md={{size: 8, offset: 2}}>
                    {
                        posts && posts?.map((post, index) => (
                            <EachPost key={index} post={post} />
                        ))
                    }
                </Col>
            </Row>
        </Container>
    </div>
  )
}
