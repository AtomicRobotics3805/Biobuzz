{
  "startPoint": {
    "x": 62.4,
    "y": 7.2,
    "locked": false,
    "headingDeg": 90
  },
  "lines": [
    {
      "id": "line-mv8ps4jn-quhav6",
      "color": "#ffc516",
      "name": "Path 1",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 4000,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 7.2,
        "y": 15.5
      },
      "controlPoints": [
        {
          "x": 28.3,
          "y": 26.163062987035634
        }
      ],
      "heading": {
        "type": "linear",
        "startDeg": 180,
        "endDeg": 270,
        "piecewiseHeading": {
          "segments": [
            {
              "startProgress": 0,
              "endProgress": 0.9568691208494221,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "startDeg": 0,
                "endDeg": 0
              }
            },
            {
              "startProgress": 0.9568691208494221,
              "endProgress": 1,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "startDeg": 0,
                "endDeg": 0
              }
            }
          ]
        },
        "degrees": 180
      }
    },
    {
      "id": "line-mv8wbdh2-bi5gyb",
      "color": "#97CC65",
      "name": "",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 2000,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 7.2,
        "y": 11
      },
      "controlPoints": [],
      "heading": {
        "type": "constant",
        "reverse": true,
        "degrees": 270
      }
    },
    {
      "id": "line-mv8wf5r9-as69rl",
      "color": "#988BCA",
      "name": "",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 4000,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 62.4,
        "y": 130
      },
      "controlPoints": [
        {
          "x": 6.213265306122449,
          "y": 32.51224489795918
        },
        {
          "x": 18.341836734693878,
          "y": 37.245918367346945
        },
        {
          "x": 95.08061224489795,
          "y": 0.23979591836734526
        },
        {
          "x": 51.09081632653061,
          "y": 59.491836734693884
        },
        {
          "x": 62.86122448979592,
          "y": 70.09081632653063
        },
        {
          "x": 65,
          "y": 70
        },
        {
          "x": 60,
          "y": 40.22448979591836
        }
      ],
      "heading": {
        "type": "piecewise",
        "reverse": true,
        "startDeg": 170,
        "endDeg": 90,
        "piecewiseHeading": {
          "segments": [
            {
              "startProgress": 0,
              "endProgress": 0.062,
              "interpolationType": "constant",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "degrees": 270
              }
            },
            {
              "startProgress": 0.062,
              "endProgress": 0.93,
              "interpolationType": "tangential",
              "reversed": true,
              "continueFromPrevious": false
            },
            {
              "startProgress": 0.93,
              "endProgress": 1,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": true,
              "parameters": {
                "startDeg": 0,
                "endDeg": 270
              }
            }
          ]
        }
      }
    }
  ],
  "shapes": [
    {
      "id": "triangle-1",
      "name": "Opposing Side",
      "vertices": [
        {
          "x": 141.5,
          "y": 0
        },
        {
          "x": 141.5,
          "y": 141.5
        },
        {
          "x": 70.75,
          "y": 141.5
        },
        {
          "x": 70.75,
          "y": 0
        }
      ],
      "color": "#dc2626",
      "fillColor": "#ff6b6b"
    },
    {
      "id": "triangle-2",
      "name": "Hive Leg",
      "vertices": [
        {
          "x": 51,
          "y": 48
        },
        {
          "x": 51,
          "y": 93
        },
        {
          "x": 45,
          "y": 93
        },
        {
          "x": 45,
          "y": 48
        }
      ],
      "color": "#2563eb",
      "fillColor": "#60a5fa"
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-mv8ps4jn-quhav6"
    },
    {
      "kind": "path",
      "lineId": "line-mv8wbdh2-bi5gyb"
    },
    {
      "kind": "path",
      "lineId": "line-mv8wf5r9-as69rl"
    }
  ],
  "fieldPoints": [],
  "version": "1.5.0",
  "timestamp": "2026-10-17T05:43:25.853Z"
}