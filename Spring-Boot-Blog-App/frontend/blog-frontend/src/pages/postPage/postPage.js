import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import { Card, CardBody, CardText, Col, Container, Row } from 'reactstrap';
import { getAllPostsByIdService } from '../../service/postService';
import { Base } from '../../component/base';
import { getCurrentUserDetails } from '../../service/sessionService';
import { BASE_URL } from '../../service/helper';

export const PostPage = () => {

    const { postId } = useParams();

    const [post, setPost] = useState([])

    function printDate(date) {
        return new Date(date).toLocaleDateString();
    }

    useEffect(() => {
        getAllPostsByIdService(postId).then(data => {
            console.log('post data ->', data)
            setPost(data)
        }).catch(erorr => {
            console.log('post page error ->', erorr)
        })
    }, [])

  return (
    <Base>
        <Container className='mt-4'>
            <Link to={`/`}>Home</Link> / {post && (<Link to={`/post/${postId}`}>{post.title}</Link>)}

            <Row>
                <Col md={{size: 12}}>
                    <Card className='m-2 p-2 border-0 shadow-sm'>
                        
                            {
                                post && (
                                    <CardBody>

                                        <CardText>Posted by: {post?.user?.username} on {printDate(post.createdDate)}</CardText>

                                        <CardText>Category: {post?.category?.name}</CardText>

                                        <div className="divder" style={{
                                            width: '100%',
                                            height: '1px',
                                            background: '#e2e2e2'

                                        }}></div>

                                        <CardText className="mt-3">
                                            <h1>{post.title}</h1>
                                        </CardText>
                                        <div className="image-container  mt-4 shadow  " style={{ maxWidth: '50%' }}>
                                            <img className="img-fluid" src={BASE_URL + '/post/image/' + post.imageName} alt="" />
                                        </div>
                                        <CardText className="mt-5" dangerouslySetInnerHTML={{ __html: post.description }}>

                                        </CardText>

                                    </CardBody>
                                    
                                )
                            }

                    </Card>
                </Col>
            </Row>
        </Container>
    </Base>
  )
}
