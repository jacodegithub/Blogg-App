import React, { useEffect, useState } from 'react'
import { Col, Container, Row } from 'reactstrap'
import { getAllPostsService } from '../service/postService';
import { EachPost } from './post';

export const Posts = ({posts}) => {

  return (
    <div>
        <Container>
            <Row>
                <Col>
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
