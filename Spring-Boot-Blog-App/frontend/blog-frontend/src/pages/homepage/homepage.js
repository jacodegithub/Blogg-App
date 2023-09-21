import React, { useState } from 'react'
import { Base } from '../../component/base'
import { Col, Container, Row } from 'reactstrap'
import { Posts } from '../../component/allPosts'

export const HomePage = () => {


  return (
    <Base>
      <Container>
        <Row>
          <Col md={{size: 8, offset: 2}}>
            <Posts />
          </Col>
        </Row>
      </Container>
    </Base>
  )
}
