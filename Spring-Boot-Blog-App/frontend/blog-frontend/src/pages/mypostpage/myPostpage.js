import React, { useEffect, useState } from 'react'
import { Base } from '../../component/base'
import { useParams } from 'react-router-dom'
import { getUserPosts } from '../../service/userService';
import { Col, Container, Row } from 'reactstrap';
import { Posts } from '../../component/allPosts';

export const UserPostpage = () => {

    const { userId } = useParams();

    const [posts, setPosts] = useState([])

    useEffect(() => {
        getUserPosts(userId).then(data => {
            console.log('data ->', data)
            setPosts(data)
        }).catch(error => {
            console.log('my post error ->', error)
        })
    }, [])


  return (
    <Base>
        <Container>
            <Row>
                <Col md={{size:8, offset: 2}}>
                    <Posts posts={posts}/>
                
                </Col>
            </Row>
        </Container>
    </Base>
  )
}
