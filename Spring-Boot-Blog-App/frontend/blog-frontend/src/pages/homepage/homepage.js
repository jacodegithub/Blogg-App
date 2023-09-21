import React, { useEffect, useState } from 'react'
import { Base } from '../../component/base'
import { Col, Container, Row } from 'reactstrap'
import { Posts } from '../../component/allPosts'
import { getAllPostsService } from '../../service/postService'

export const HomePage = () => {

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
    <Base>
      <Container>
        <Row>
          <Col md={{size: 8, offset: 2}}>
            <Posts posts={posts} />
          </Col>
        </Row>
      </Container>
    </Base>
  )
}
